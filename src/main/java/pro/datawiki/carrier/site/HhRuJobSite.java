package pro.datawiki.carrier.site;

import pro.datawiki.carrier.dto.VacancyDto;
import pro.datawiki.carrier.dto.enums.Site;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HhRuJobSite implements JobSite {

    public static final HhRuJobSite INSTANCE = new HhRuJobSite();

    private static final String BASE_URL = "https://hh.ru";
    private static final String HOST = "hh.ru";
    private static final String LOGIN_URL = "https://hh.ru/account/login";
    private static final String SIGNUP_URL = "https://hh.ru/account/signup";
    private static final String RESUMES_URL = "https://hh.ru/applicant/resumes";
    private static final String NEGOTIATIONS_URL = "https://hh.ru/applicant/negotiations";
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";

    // DOM Selectors
    public static final String SELECTOR_LOGIN_BUTTON = "a[data-qa=\"mainmenu_profile-link\"]:has-text(\"Войти\")";
    public static final String SELECTOR_STANDARD_LOGIN = "[data-qa=\"login\"], [data-qa=\"mainmenu_login\"]";
    public static final String SELECTOR_AUTH_CHECK = "[data-qa=\"mainmenu_applicantProfile\"], [data-qa=\"mainmenu_profile\"]";
    public static final String SELECTOR_RESUME_RAISE = "[data-qa=\"resume-update-button\"]";
    public static final String SELECTOR_VACANCY_TITLE = "[data-qa='vacancy-title']";
    public static final String SELECTOR_COMPANY_NAME = "[data-qa='vacancy-company-name']";
    public static final String SELECTOR_COMPANY_DESCRIPTION = "[data-qa='vacancy-company-description']";
    public static final String SELECTOR_EMPLOYER_DESCRIPTION = "[data-qa='employer-description']";
    public static final String SELECTOR_DESCRIPTION = "[data-qa='vacancy-description']";
    public static final String SELECTOR_EXPERIENCE = "[data-qa='vacancy-experience']";
    public static final String SELECTOR_WORK_CONDITIONS = "[data-qa='vacancy-view-employment-mode'], [data-qa='common-employment-text']";
    public static final String SELECTOR_LOCATION = "[data-qa='vacancy-view-location']";
    public static final String SELECTOR_RATING = "[data-qa='employer-review-small-widget-total-rating']";
    public static final String SELECTOR_SKILLS = "[data-qa='skills-element']";
    public static final String SELECTOR_TRUSTED_EMPLOYER = "[data-qa='trusted-employer-link']";
    public static final String SELECTOR_SEARCH_CARDS = "[data-qa=\"vacancy-serp__vacancy\"]";
    public static final String SELECTOR_SEARCH_HEADER = "h1[data-qa=\"title\"]";
    public static final String SELECTOR_SEARCH_ITEM_TITLE = "[data-qa=\"serp-item__title\"]";
    public static final String SELECTOR_SEARCH_ITEM_EMPLOYER = "[data-qa=\"vacancy-serp__vacancy-employer\"]";
    public static final String SELECTOR_SEARCH_ITEM_ADDRESS = "[data-qa=\"vacancy-serp__vacancy-address\"]";
    public static final String SELECTOR_SEARCH_ITEM_COMPENSATION = "[data-qa=\"vacancy-serp__vacancy-compensation\"]";
    public static final String SELECTOR_SEARCH_ITEM_TAGS = ".magritte-tag__label___YHV-o_5-1-4 span, .compensation-labels_magritte--pbBIkJ7Ww24ZILKz span, [data-qa^=\"vacancy-label-\"], [data-qa^=\"vacancy-serp__vacancy-work-experience-\"]";
    public static final String SELECTOR_SEARCH_HIDE_BUTTON = "[data-qa=\"vacancy-serp__vacancy-hide\"], [data-qa=\"vacancy__blacklist-show-add_narrow-card\"], [data-qa=\"vacancy__blacklist-show-add\"], [aria-label=\"Скрыть\"]";
    public static final String SELECTOR_SEARCH_HIDE_MENU = "[data-qa=\"vacancy__blacklist-menu-add-vacancy\"]";
    public static final String SELECTOR_RESPOND_TOP = "[data-qa=\"vacancy-response-link-top\"]";
    public static final String SELECTOR_RESPOND_BOTTOM = "[data-qa=\"vacancy-response-link-bottom\"]";
    public static final String SELECTOR_ALREADY_APPLIED = "[data-qa=\"vacancy-response-link-view-topic\"]";
    public static final String SELECTOR_APPLIED_SUCCESS_TEXT = "text=Вы откликнулись";
    public static final String SELECTOR_MODAL = "[data-qa=\"bloko-modal\"]";
    public static final String SELECTOR_RESUME_CARDS = "[data-qa=\"resume\"]";
    public static final String SELECTOR_RESUME_TITLE = "[data-qa=\"resume-title\"], [data-qa=\"title\"]";
    public static final String SELECTOR_NEGOTIATIONS_ITEM = "[data-qa=\"negotiations-item\"]";
    public static final String SELECTOR_NEGOTIATIONS_UNREAD = "[data-qa=\"negotiations-item-unread\"]";
    public static final String SELECTOR_NEGOTIATIONS_EMPLOYER = "[data-qa=\"negotiations-item-employer\"]";
    public static final String SELECTOR_NEGOTIATIONS_VACANCY = "[data-qa=\"negotiations-item-vacancy\"]";
    public static final String SELECTOR_NEGOTIATIONS_STATUS = "[data-qa=\"negotiations-item-status\"]";

    protected HhRuJobSite() {
    }

    @Override
    public Site getSite() {
        return Site.HHRU;
    }

    @Override
    public String getSiteName() {
        return "hh";
    }

    @Override
    public String getBaseUrl() {
        return BASE_URL;
    }

    @Override
    public String getHost() {
        return HOST;
    }

    @Override
    public String getLoginUrl() {
        return LOGIN_URL;
    }

    @Override
    public String getSignupUrl() {
        return SIGNUP_URL;
    }

    @Override
    public String getResumesUrl() {
        return RESUMES_URL;
    }

    @Override
    public String getNegotiationsUrl() {
        return NEGOTIATIONS_URL;
    }

    @Override
    public String getVacancyUrl(String externalId) {
        if (externalId == null || externalId.isBlank()) {
            return BASE_URL;
        }
        return BASE_URL + "/vacancy/" + externalId;
    }

    @Override
    public String getResumeUrl(String resumeId) {
        if (resumeId == null || resumeId.isBlank()) {
            return RESUMES_URL;
        }
        return BASE_URL + "/resume/" + resumeId;
    }

    @Override
    public String buildSearchUrl(String query, int page, Integer salaryFrom, boolean remoteOnly, String location) {
        StringBuilder sb = new StringBuilder(BASE_URL).append("/search/vacancy?");
        if (query != null && !query.isBlank()) {
            sb.append("text=").append(URLEncoder.encode(query, StandardCharsets.UTF_8));
        }
        if (page > 1) {
            sb.append("&page=").append(page);
        }
        if (salaryFrom != null) {
            sb.append("&salary=").append(salaryFrom).append("&only_with_salary=true");
        }
        if (remoteOnly) {
            sb.append("&schedule=remote");
        }
        if (location != null && !location.isBlank()) {
            sb.append("&area=").append(URLEncoder.encode(location, StandardCharsets.UTF_8));
        }
        return sb.toString();
    }

    @Override
    public String getUserAgent() {
        return USER_AGENT;
    }

    @Override
    public boolean isRussianProxyRequired() {
        return true;
    }

    @Override
    public long getDefaultPacingDelayMs() {
        return 1000L;
    }

    @Override
    public String getStorageProfilePath(String candidateId) {
        String baseStorage = System.getenv("CANDIDATE_STORAGE_PATH");
        if (baseStorage == null || baseStorage.isBlank()) {
            baseStorage = "/data/candidate-storage";
        }
        return baseStorage + "/" + candidateId + "/profiles/" + getSiteName();
    }

    @Override
    public String getLoginButtonSelector() {
        return SELECTOR_LOGIN_BUTTON;
    }

    @Override
    public String getStandardLoginSelector() {
        return SELECTOR_STANDARD_LOGIN;
    }

    @Override
    public String getAuthCheckSelector() {
        return SELECTOR_AUTH_CHECK;
    }

    @Override
    public String getResumeRaiseSelector() {
        return SELECTOR_RESUME_RAISE;
    }

    @Override
    public String getVacancyTitleSelector() {
        return SELECTOR_VACANCY_TITLE;
    }

    @Override
    public String getCompanyNameSelector() {
        return SELECTOR_COMPANY_NAME;
    }

    @Override
    public String getCompanyDescriptionSelector() {
        return SELECTOR_COMPANY_DESCRIPTION;
    }

    @Override
    public String getEmployerDescriptionSelector() {
        return SELECTOR_EMPLOYER_DESCRIPTION;
    }

    @Override
    public String getDescriptionSelector() {
        return SELECTOR_DESCRIPTION;
    }

    @Override
    public String getExperienceSelector() {
        return SELECTOR_EXPERIENCE;
    }

    @Override
    public String getWorkConditionsSelector() {
        return SELECTOR_WORK_CONDITIONS;
    }

    @Override
    public String getLocationSelector() {
        return SELECTOR_LOCATION;
    }

    @Override
    public String getCompanyRatingSelector() {
        return SELECTOR_RATING;
    }

    @Override
    public String getSkillsSelector() {
        return SELECTOR_SKILLS;
    }

    @Override
    public String getTrustedEmployerSelector() {
        return SELECTOR_TRUSTED_EMPLOYER;
    }

    @Override
    public String getSearchCardsSelector() {
        return SELECTOR_SEARCH_CARDS;
    }

    @Override
    public String getSearchHeaderSelector() {
        return SELECTOR_SEARCH_HEADER;
    }

    @Override
    public String getSearchItemTitleSelector() {
        return SELECTOR_SEARCH_ITEM_TITLE;
    }

    @Override
    public String getSearchItemEmployerSelector() {
        return SELECTOR_SEARCH_ITEM_EMPLOYER;
    }

    @Override
    public String getSearchItemAddressSelector() {
        return SELECTOR_SEARCH_ITEM_ADDRESS;
    }

    @Override
    public String getSearchItemCompensationSelector() {
        return SELECTOR_SEARCH_ITEM_COMPENSATION;
    }

    @Override
    public String getSearchItemTagsSelector() {
        return SELECTOR_SEARCH_ITEM_TAGS;
    }

    @Override
    public String getSearchHideButtonSelector() {
        return SELECTOR_SEARCH_HIDE_BUTTON;
    }

    @Override
    public String getSearchHideMenuSelector() {
        return SELECTOR_SEARCH_HIDE_MENU;
    }

    @Override
    public String getRespondTopButtonSelector() {
        return SELECTOR_RESPOND_TOP;
    }

    @Override
    public String getRespondBottomButtonSelector() {
        return SELECTOR_RESPOND_BOTTOM;
    }

    @Override
    public String getAlreadyAppliedSelector() {
        return SELECTOR_ALREADY_APPLIED;
    }

    @Override
    public String getAppliedSuccessTextSelector() {
        return SELECTOR_APPLIED_SUCCESS_TEXT;
    }

    @Override
    public String getModalSelector() {
        return SELECTOR_MODAL;
    }

    @Override
    public String getResumeCardsSelector() {
        return SELECTOR_RESUME_CARDS;
    }

    @Override
    public String getResumeTitleSelector() {
        return SELECTOR_RESUME_TITLE;
    }

    @Override
    public String getNegotiationsItemSelector() {
        return SELECTOR_NEGOTIATIONS_ITEM;
    }

    @Override
    public String getNegotiationsUnreadSelector() {
        return SELECTOR_NEGOTIATIONS_UNREAD;
    }

    @Override
    public String getNegotiationsEmployerSelector() {
        return SELECTOR_NEGOTIATIONS_EMPLOYER;
    }

    @Override
    public String getNegotiationsVacancySelector() {
        return SELECTOR_NEGOTIATIONS_VACANCY;
    }

    @Override
    public String getNegotiationsStatusSelector() {
        return SELECTOR_NEGOTIATIONS_STATUS;
    }

    @Override
    public ParsedSalary parseSalary(String rawCompensation) {
        if (rawCompensation == null || rawCompensation.isBlank()) {
            return new ParsedSalary(null, null, null, null);
        }
        String clean = rawCompensation.replaceAll("[\\s\\u00A0\\u2007\\u202F]+", " ").trim();
        Integer salaryFrom = null;
        Integer salaryTo = null;
        String currency = "RUB";
        if (clean.contains("USD") || clean.contains("$")) {
            currency = "USD";
        } else if (clean.contains("EUR") || clean.contains("€")) {
            currency = "EUR";
        } else if (clean.contains("KZT") || clean.contains("₸")) {
            currency = "KZT";
        } else if (clean.contains("BYN")) {
            currency = "BYN";
        }

        Boolean isGross = null;
        if (clean.toLowerCase().contains("до вычета")) {
            isGross = Boolean.TRUE;
        } else if (clean.toLowerCase().contains("на руки")) {
            isGross = Boolean.FALSE;
        }

        String digitsOnly = clean.replaceAll("[^0-9–-]", " ").trim();
        String[] parts = digitsOnly.split("[–-]");

        Pattern numPattern = Pattern.compile("\\d+");
        if (parts.length == 2) {
            Matcher m1 = numPattern.matcher(parts[0].replaceAll("\\s+", ""));
            if (m1.find()) {
                salaryFrom = Integer.parseInt(m1.group());
            }
            Matcher m2 = numPattern.matcher(parts[1].replaceAll("\\s+", ""));
            if (m2.find()) {
                salaryTo = Integer.parseInt(m2.group());
            }
        } else {
            String noSpaces = clean.replaceAll("[\\s\\u00A0\\u2007\\u202F]+", "");
            if (noSpaces.startsWith("от")) {
                String val = noSpaces.replaceAll("[^0-9]", "");
                if (!val.isEmpty()) salaryFrom = Integer.parseInt(val);
            } else if (noSpaces.startsWith("до")) {
                String val = noSpaces.replaceAll("[^0-9]", "");
                if (!val.isEmpty()) salaryTo = Integer.parseInt(val);
            }
        }

        return new ParsedSalary(salaryFrom, salaryTo, currency, isGross);
    }

    @Override
    public VacancyDto normalizeVacancy(VacancyDto vacancy) {
        if (vacancy == null) return null;
        vacancy.setPrimarySourceSite(Site.HHRU);
        if (vacancy.getUrl() != null && vacancy.getPrimaryExternalId() == null && vacancy.getUrl().contains("/vacancy/")) {
            String[] parts = vacancy.getUrl().split("/vacancy/");
            if (parts.length > 1) {
                vacancy.setPrimaryExternalId(parts[1].split("[/?#]")[0]);
            }
        }
        return vacancy;
    }
}
