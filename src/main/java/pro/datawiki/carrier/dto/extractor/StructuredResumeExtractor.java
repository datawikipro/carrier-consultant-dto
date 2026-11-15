package pro.datawiki.carrier.dto.extractor;

import pro.datawiki.carrier.dto.track.CareerTrack;
import pro.datawiki.carrier.dto.track.CareerTrackRegistry;

import java.time.Year;
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

    // Date range pattern for work experience entries (Russian and English)
    private static final Pattern WORK_EXP_DATE_PATTERN = Pattern.compile(
            "(?ui)(?:([а-яa-z]{3,12})[ \\t]+)?(\\d{4})[ \\t]*(?:—|-|–|по|to)[ \\t]*(?:([а-яa-z]{3,12})[ \\t]+)?(\\d{4}|по\\s+настоящее\\s+время|настоящее\\s+время|сейчас|present|current)"
    );

    // City patterns
    private static final Set<String> KNOWN_CITIES = Set.of(
            "москва", "санкт-петербург", "питер", "новосибирск", "екатеринбург", "казань",
            "нижний новгород", "самара", "ростов-на-дону", "краснодар", "воронеж", "уфа",
            "пермь", "красноярск", "минск", "алматы", "астана", "тбилиси", "ереван",
            "баку", "ташкент", "белград", "нови-сад", "лимасол", "подгорица", "берлин", "лондон", "дубай"
    );

    private static boolean isKnownCity(String candidate) {
        if (candidate == null || candidate.isBlank()) return false;
        String clean = candidate.trim().toLowerCase().replaceAll("^[г\\.]+\\s*", "");
        return KNOWN_CITIES.contains(clean);
    }

    private static final Pattern CITY_PATTERN = Pattern.compile(
            "(?ui)\\b(Москва|Санкт-Петербург|Новосибирск|Екатеринбург|Казань|Нижний\\s+Новгород|Самара|Ростов-на-Дону|Краснодар|Воронеж|Уфа|Пермь|Красноярск|Минск|Алматы|Астана|Тбилиси|Ереван|Баку|Ташкент|Белград|Нови-Сад|Лимасол|Подгорица|Берлин|Лондон|Дубай)\\b"
    );

    // Section delimiter patterns
    private static final Pattern EDUCATION_HEADER_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n)[ \\t]*(?:образование|education|высшее\\s+образование)[ \\t]*:?[ \\t]*(?:\\n|$)"
    );

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

        // 2. Extract City
        Matcher mCity = CITY_PATTERN.matcher(rawContent);
        if (mCity.find()) {
            builder.city(mCity.group(1));
        }

        // 3. Extract Experience Years Total
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

        // 4. Extract Salary
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

        // 5. Extract Structured Work Experiences
        List<StructuredCandidateProfile.StructuredExperienceItem> experiences = extractWorkExperiences(rawContent);
        builder.experiences(experiences);

        // 6. Extract Skills using the indexed taxonomy
        StructuredVacancyRequirements skillWrapper = DeterministicRequirementsExtractor.extract(rawContent, fallbackPosition);
        Set<String> aggregatedSkills = new LinkedHashSet<>(skillWrapper.getAllExtractedSkills());

        // Also aggregate any skills detected specifically inside individual experience blocks
        for (StructuredCandidateProfile.StructuredExperienceItem exp : experiences) {
            if (exp.getExtractedSkills() != null) {
                aggregatedSkills.addAll(exp.getExtractedSkills());
            }
        }

        builder.allSkills(aggregatedSkills);
        builder.hardSkills(skillWrapper.getHardSkills());
        builder.tooling(skillWrapper.getTooling());
        builder.softSkills(skillWrapper.getSoftSkills());

        // 7. Detect Career Track & Grade
        CareerTrack track = CareerTrackRegistry.resolveTrackForTitle(fallbackPosition);
        builder.careerTrackId(track.getTrackId());
        builder.grade(skillWrapper.getGrade());
        builder.desiredPosition(fallbackPosition != null ? fallbackPosition : track.getDisplayName());

        return builder.build();
    }

    private static List<StructuredCandidateProfile.StructuredExperienceItem> extractWorkExperiences(String text) {
        List<StructuredCandidateProfile.StructuredExperienceItem> list = new ArrayList<>();
        if (text == null || text.isBlank()) {
            return list;
        }

        // Locate education boundary to avoid parsing university years as work experience
        int eduStart = text.length();
        Matcher mEdu = EDUCATION_HEADER_PATTERN.matcher(text);
        if (mEdu.find()) {
            eduStart = mEdu.start();
        }

        String expSlice = text.substring(0, eduStart);

        record ExpMatch(int start, int end, Integer startYear, Integer startMonth, Integer endYear, Integer endMonth, boolean isCurrent, String period) {}
        List<ExpMatch> matches = new ArrayList<>();

        Matcher m = WORK_EXP_DATE_PATTERN.matcher(expSlice);
        while (m.find()) {
            try {
                Integer sYear = Integer.parseInt(m.group(2));
                Integer sMonth = parseMonth(m.group(1));

                String endVal = m.group(4);
                Integer eYear;
                Integer eMonth = parseMonth(m.group(3));
                boolean current = false;

                if (endVal.matches("(?ui)по\\s+настоящее\\s+время|настоящее\\s+время|сейчас|present|current")) {
                    current = true;
                    eYear = Year.now().getValue();
                } else {
                    eYear = Integer.parseInt(endVal);
                }

                String periodStr = m.group(0).trim();
                matches.add(new ExpMatch(m.start(), m.end(), sYear, sMonth, eYear, eMonth, current, periodStr));
            } catch (Exception ignored) {}
        }

        for (int i = 0; i < matches.size(); i++) {
            ExpMatch currentMatch = matches.get(i);
            int nextStart = (i + 1 < matches.size()) ? matches.get(i + 1).start() : expSlice.length();

            String chunk = expSlice.substring(currentMatch.end(), nextStart).trim();
            String[] rawLines = chunk.split("\\r?\\n");
            List<String> validLines = new ArrayList<>();

            for (String line : rawLines) {
                String trimmed = line.trim();
                // Filter out duration annotations like "(2 года 3 месяца)"
                if (!trimmed.isEmpty() && !trimmed.matches("^[\\(\\[].*(?:год|лет|месяц|yr|mo).*[\\)\\]]$")) {
                    validLines.add(trimmed);
                }
            }

            if (validLines.isEmpty()) {
                continue;
            }

            String companyName = validLines.get(0);
            String position = "Специалист";
            String city = null;
            String department = null;
            StringBuilder descBuilder = new StringBuilder();

            int lineIdx = 1;
            if (lineIdx < validLines.size()) {
                String candidateLine = validLines.get(lineIdx);
                if (isKnownCity(candidateLine)) {
                    city = candidateLine;
                    lineIdx++;
                } else {
                    Matcher cityMatcher = CITY_PATTERN.matcher(candidateLine);
                    if (cityMatcher.find()) {
                        city = cityMatcher.group(1);
                        lineIdx++;
                    }
                }
            }

            if (lineIdx < validLines.size()) {
                position = validLines.get(lineIdx++);
            }

            for (; lineIdx < validLines.size(); lineIdx++) {
                String l = validLines.get(lineIdx);
                if (department == null && l.matches("(?ui).*(?:отдел|департамент|команда|подразделение|department|team).*")) {
                    department = l;
                } else {
                    if (descBuilder.length() > 0) {
                        descBuilder.append("\n");
                    }
                    descBuilder.append(l);
                }
            }

            String description = descBuilder.toString().trim();
            Set<String> expSkills = DeterministicRequirementsExtractor.extractSkills(chunk);

            StructuredCandidateProfile.StructuredExperienceItem item = StructuredCandidateProfile.StructuredExperienceItem.builder()
                    .companyName(companyName)
                    .position(position)
                    .city(city)
                    .department(department)
                    .period(currentMatch.period())
                    .startYear(currentMatch.startYear())
                    .startMonth(currentMatch.startMonth())
                    .endYear(currentMatch.endYear())
                    .endMonth(currentMatch.endMonth())
                    .isCurrent(currentMatch.isCurrent())
                    .description(description)
                    .extractedSkills(expSkills)
                    .build();

            list.add(item);
        }

        return list;
    }

    private static Integer parseMonth(String monthStr) {
        if (monthStr == null || monthStr.isBlank()) {
            return null;
        }
        String lower = monthStr.toLowerCase(Locale.ROOT);
        if (lower.startsWith("янв") || lower.startsWith("jan")) return 1;
        if (lower.startsWith("фев") || lower.startsWith("feb")) return 2;
        if (lower.startsWith("мар") || lower.startsWith("mar")) return 3;
        if (lower.startsWith("апр") || lower.startsWith("apr")) return 4;
        if (lower.startsWith("май") || lower.startsWith("мая") || lower.startsWith("may")) return 5;
        if (lower.startsWith("июн") || lower.startsWith("jun")) return 6;
        if (lower.startsWith("июл") || lower.startsWith("jul")) return 7;
        if (lower.startsWith("авг") || lower.startsWith("aug")) return 8;
        if (lower.startsWith("сен") || lower.startsWith("sep")) return 9;
        if (lower.startsWith("окт") || lower.startsWith("oct")) return 10;
        if (lower.startsWith("ноя") || lower.startsWith("nov")) return 11;
        if (lower.startsWith("дек") || lower.startsWith("dec")) return 12;
        return null;
    }
}
