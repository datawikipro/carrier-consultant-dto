package pro.datawiki.carrier.dto.jobsite;

public class HhRuJobSite extends AbstractJobSite {

    public static final HhRuJobSite INSTANCE = new HhRuJobSite();

    @Override
    public String getCode() {
        return "HHRU";
    }

    @Override
    public String getSlug() {
        return "hh-ru";
    }

    @Override
    public String getDisplayName() {
        return "HeadHunter (hh.ru)";
    }

    @Override
    public String getBaseUrl() {
        return "https://hh.ru";
    }

    @Override
    public String getLoginUrl() {
        return "https://hh.ru/account/login";
    }

    @Override
    public boolean isRussianResidentialProxyRequired() {
        return true;
    }

    @Override
    public long getPacingDelayMs() {
        return 1800L;
    }

    @Override
    public String getAuthVerificationSelector() {
        return "[data-qa='mainmenu_applicantProfile'], [data-qa='header-profile-icon'], a[href*='/applicant/resumes']";
    }

    @Override
    public String getResumeBoostSelector() {
        return "button[data-qa='resume-update-button'], [data-qa='resume-recommendations__boost-button']";
    }

    @Override
    public String getResumesPageUrl() {
        return "https://hh.ru/applicant/resumes";
    }

    @Override
    public String buildSearchUrl(String query, int page) {
        StringBuilder sb = new StringBuilder(getBaseUrl()).append("/search/vacancy?");
        if (query != null && !query.isBlank()) {
            sb.append("text=").append(java.net.URLEncoder.encode(query, java.nio.charset.StandardCharsets.UTF_8));
        }
        sb.append("&search_field=name&search_field=description&order_by=relevance");
        if (page > 1) {
            sb.append("&page=").append(page - 1);
        }
        return sb.toString();
    }

    @Override
    public String getSearchCardsSelector() {
        return "[data-qa='vacancy-serp__vacancy']";
    }

    @Override
    public String getSearchItemTitleSelector() {
        return "a[data-qa='serp-item__title'], a[href*='/vacancy/']";
    }

    @Override
    public String getVacancyTitleSelector() {
        return "[data-qa='vacancy-title'], h1";
    }

    @Override
    public String getCompanyNameSelector() {
        return "[data-qa='vacancy-company-name'], [data-qa='vacancy-company-name-wrapper']";
    }

    @Override
    public String getCompanyUrlSelector() {
        return "[data-qa='vacancy-company-name'] a, [data-qa='vacancy-company-name-wrapper'] a";
    }

    @Override
    public String getDescriptionSelector() {
        return "[data-qa='vacancy-description']";
    }

    @Override
    public String getExperienceSelector() {
        return "[data-qa='vacancy-experience']";
    }

    @Override
    public String getWorkConditionsSelector() {
        return "[data-qa='vacancy-view-employment-mode'], [data-qa='common-employment-text']";
    }

    @Override
    public String getLocationSelector() {
        return "[data-qa='vacancy-view-raw-address'], [data-qa='vacancy-view-location']";
    }

    @Override
    public String getSkillsSelector() {
        return "[data-qa='skills-element'], .bloko-tag, [data-qa='bloko-tag__text']";
    }

    @Override
    public String getSalarySelector() {
        return "[data-qa='vacancy-salary'], [data-qa='vacancy-salary-compensation-type-net'], [data-qa='vacancy-salary-compensation-type-gross'], .magritte-salary";
    }

    @Override
    public String getApplyButtonSelector() {
        return "[data-qa='vacancy-response-link-top'], [data-qa='vacancy-response-link-bottom']";
    }

    @Override
    public String getPublicationTimeSelector() {
        return "time[dateTime], [data-qa='vacancy-view-publication-date'] time, time";
    }
}
