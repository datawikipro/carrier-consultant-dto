package pro.datawiki.carrier.dto.jobsite;

import lombok.Getter;

@Getter
public abstract class AbstractJobSite implements JobSite {

    @Override
    public boolean supportsResumeBoost() {
        return getResumeBoostSelector() != null && !getResumeBoostSelector().isBlank();
    }

    @Override
    public boolean supportsDirectApply() {
        return true;
    }

    @Override
    public boolean supportsChat() {
        return true;
    }

    @Override
    public String buildSearchUrl(String query, int page) {
        return getBaseUrl() + "/search";
    }

    @Override
    public String getSearchCardsSelector() {
        return ".vacancy-card";
    }

    @Override
    public String getSearchItemTitleSelector() {
        return "a[href*='/vacancy/']";
    }

    @Override
    public String getVacancyTitleSelector() {
        return "h1";
    }

    @Override
    public String getCompanyNameSelector() {
        return ".company-name";
    }

    @Override
    public String getCompanyUrlSelector() {
        return "a[href*='/employer/']";
    }

    @Override
    public String getDescriptionSelector() {
        return ".vacancy-description";
    }

    @Override
    public String getExperienceSelector() {
        return ".experience";
    }

    @Override
    public String getWorkConditionsSelector() {
        return ".work-conditions";
    }

    @Override
    public String getLocationSelector() {
        return ".location";
    }

    @Override
    public String getSkillsSelector() {
        return ".skill";
    }

    @Override
    public String getSalarySelector() {
        return ".salary";
    }

    @Override
    public String getApplyButtonSelector() {
        return "button.apply";
    }

    @Override
    public String getPublicationTimeSelector() {
        return "time[dateTime], time";
    }

    @Override
    public String toString() {
        return getCode() + " (" + getDisplayName() + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobSite other)) return false;
        return getCode().equalsIgnoreCase(other.getCode());
    }

    @Override
    public int hashCode() {
        return getCode().toUpperCase().hashCode();
    }
}
