package pro.datawiki.carrier.dto.extractor;

import pro.datawiki.carrier.dto.track.CareerTrack;
import pro.datawiki.carrier.dto.track.CareerTrackRegistry;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Deterministic resume extractor for parsing candidate profiles, skills,
 * work history, education, and contact details from raw HTML or text.
 */
public class StructuredResumeExtractor {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    private static final Pattern TELEGRAM_PATTERN = Pattern.compile("(?<![a-zA-Z0-9._%+-])(?:https?://(?:www\\.)?t\\.me/|@)([a-zA-Z0-9_]{4,32})", Pattern.CASE_INSENSITIVE);
    private static final Pattern PHONE_PATTERN = Pattern.compile("(?:\\+7|8)[\\s(-]*(\\d{3})[\\s)-]*(\\d{3})[\\s-]*(\\d{2})[\\s-]*(\\d{2})");
    private static final Pattern GITHUB_PATTERN = Pattern.compile("https?://(?:www\\.)?github\\.com/([a-zA-Z0-9_-]+)", Pattern.CASE_INSENSITIVE);
    private static final Pattern LINKEDIN_PATTERN = Pattern.compile("https?://(?:www\\.)?linkedin\\.com/in/([a-zA-Z0-9_-]+)", Pattern.CASE_INSENSITIVE);

    private static final Pattern SALARY_PATTERN = Pattern.compile("(\\d[\\d\\s.,]{2,10})\\s*(₽|руб|usd|\\$|eur|€)?", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern EXP_YEARS_PATTERN = Pattern.compile("(\\d+)\\s*(?:лет|года?|г\\.?|years?|yrs?)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
    private static final Pattern EXP_MONTHS_PATTERN = Pattern.compile("(\\d+)\\s*(?:месяц(?:ев|а)?|мес\\.?|months?)", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);

    public static StructuredCandidateProfile extract(String rawContent, String fallbackPosition) {
        StructuredCandidateProfile.StructuredCandidateProfileBuilder builder = StructuredCandidateProfile.builder();

        if (rawContent == null || rawContent.isBlank()) {
            CareerTrack defaultTrack = CareerTrackRegistry.resolveTrackForTitle(fallbackPosition);
            return builder
                    .desiredPosition(fallbackPosition != null ? fallbackPosition : "Developer")
                    .careerTrackId(defaultTrack.getTrackId())
                    .grade("MIDDLE")
                    .build();
        }

        // 1. Extract Contacts
        Map<String, String> contacts = new LinkedHashMap<>();
        Matcher mEmail = EMAIL_PATTERN.matcher(rawContent);
        if (mEmail.find()) {
            contacts.put("EMAIL", mEmail.group(0));
        }

        Matcher mTg = TELEGRAM_PATTERN.matcher(rawContent);
        if (mTg.find()) {
            contacts.put("TELEGRAM", "@" + mTg.group(1));
        }

        Matcher mPhone = PHONE_PATTERN.matcher(rawContent);
        if (mPhone.find()) {
            contacts.put("PHONE", mPhone.group(0));
        }

        Matcher mGithub = GITHUB_PATTERN.matcher(rawContent);
        if (mGithub.find()) {
            contacts.put("GITHUB", mGithub.group(0));
        }

        Matcher mLinkedin = LINKEDIN_PATTERN.matcher(rawContent);
        if (mLinkedin.find()) {
            contacts.put("LINKEDIN", mLinkedin.group(0));
        }
        builder.contacts(contacts);

        // 2. Extract Experience Years
        double totalExpYears = 0.0;
        Matcher mExpY = EXP_YEARS_PATTERN.matcher(rawContent);
        if (mExpY.find()) {
            try {
                totalExpYears += Double.parseDouble(mExpY.group(1));
            } catch (Exception ignored) {}
        }
        Matcher mExpM = EXP_MONTHS_PATTERN.matcher(rawContent);
        if (mExpM.find()) {
            try {
                totalExpYears += Double.parseDouble(mExpM.group(1)) / 12.0;
            } catch (Exception ignored) {}
        }
        if (totalExpYears > 0.0) {
            builder.experienceYearsTotal(Math.round(totalExpYears * 10.0) / 10.0);
        }

        // 3. Extract Salary
        Matcher mSal = SALARY_PATTERN.matcher(rawContent);
        if (mSal.find()) {
            try {
                String numStr = mSal.group(1).replaceAll("[\\s.,]", "");
                int sal = Integer.parseInt(numStr);
                if (sal >= 10000) {
                    builder.desiredSalaryMin(sal);
                    String curr = mSal.group(2);
                    if (curr != null) {
                        String lowerCurr = curr.toLowerCase();
                        if (lowerCurr.contains("usd") || lowerCurr.contains("$")) builder.salaryCurrency("USD");
                        else if (lowerCurr.contains("eur") || lowerCurr.contains("€")) builder.salaryCurrency("EUR");
                        else builder.salaryCurrency("RUB");
                    } else {
                        builder.salaryCurrency("RUB");
                    }
                }
            } catch (Exception ignored) {}
        }

        // 4. Extract Skills using the indexed taxonomy
        StructuredVacancyRequirements skillWrapper = DeterministicRequirementsExtractor.extract(rawContent, fallbackPosition);
        builder.allSkills(skillWrapper.getAllExtractedSkills());
        builder.hardSkills(skillWrapper.getHardSkills());
        builder.tooling(skillWrapper.getTooling());
        builder.softSkills(skillWrapper.getSoftSkills());

        // 5. Detect Career Track & Grade
        CareerTrack track = CareerTrackRegistry.resolveTrackForTitle(fallbackPosition);
        builder.careerTrackId(track.getTrackId());
        builder.grade(skillWrapper.getGrade());
        builder.desiredPosition(fallbackPosition != null ? fallbackPosition : track.getDisplayName());

        return builder.build();
    }
}
