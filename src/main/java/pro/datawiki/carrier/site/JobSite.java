package pro.datawiki.carrier.site;

import pro.datawiki.carrier.dto.VacancyDto;
import pro.datawiki.carrier.dto.enums.Site;

/**
 * Domain contract for job sites in the CareerLab ecosystem.
 * Defines network policies, DOM selectors, anti-fraud profiles,
 * and canonical mapping strategies.
 */
public interface JobSite {

    // --- Network and Anti-Fraud ---
    Site getSite();
    String getSiteName();
    String getBaseUrl();
    String getHost();
    String getLoginUrl();
    String getSignupUrl();
    String getResumesUrl();
    String getNegotiationsUrl();
    String getVacancyUrl(String externalId);
    String getResumeUrl(String resumeId);
    String buildSearchUrl(String query, int page, Integer salaryFrom, boolean remoteOnly, String location);
    String getUserAgent();
    boolean isRussianProxyRequired();
    long getDefaultPacingDelayMs();
    String getStorageProfilePath(String candidateId);

    // --- DOM Selectors ---
    String getLoginButtonSelector();
    String getStandardLoginSelector();
    String getAuthCheckSelector();
    String getResumeRaiseSelector();
    String getVacancyTitleSelector();
    String getCompanyNameSelector();
    String getCompanyDescriptionSelector();
    String getEmployerDescriptionSelector();
    String getDescriptionSelector();
    String getExperienceSelector();
    String getWorkConditionsSelector();
    String getLocationSelector();
    String getCompanyRatingSelector();
    String getSkillsSelector();
    String getTrustedEmployerSelector();
    String getSearchCardsSelector();
    String getSearchHeaderSelector();
    String getSearchItemTitleSelector();
    String getSearchItemEmployerSelector();
    String getSearchItemAddressSelector();
    String getSearchItemCompensationSelector();
    String getSearchItemTagsSelector();
    String getSearchHideButtonSelector();
    String getSearchHideMenuSelector();
    String getRespondTopButtonSelector();
    String getRespondBottomButtonSelector();
    String getAlreadyAppliedSelector();
    String getAppliedSuccessTextSelector();
    String getModalSelector();
    String getResumeCardsSelector();
    String getResumeTitleSelector();
    String getNegotiationsItemSelector();
    String getNegotiationsUnreadSelector();
    String getNegotiationsEmployerSelector();
    String getNegotiationsVacancySelector();
    String getNegotiationsStatusSelector();

    // --- Parsing and Normalization Helpers ---
    ParsedSalary parseSalary(String rawCompensation);
    VacancyDto normalizeVacancy(VacancyDto vacancy);

    record ParsedSalary(Integer salaryFrom, Integer salaryTo, String currency, Boolean isGross) {}
}
