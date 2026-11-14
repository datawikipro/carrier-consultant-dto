package pro.datawiki.carrier.dto.jobsite;

public class HabrCareerJobSite extends AbstractJobSite {

    public static final HabrCareerJobSite INSTANCE = new HabrCareerJobSite();

    @Override
    public String getCode() {
        return "HABR";
    }

    @Override
    public String getSlug() {
        return "habr";
    }

    @Override
    public String getDisplayName() {
        return "Хабр Карьера (career.habr.ru)";
    }

    @Override
    public String getBaseUrl() {
        return "https://career.habr.ru";
    }

    @Override
    public String getLoginUrl() {
        return "https://account.habr.com/login/";
    }

    @Override
    public boolean isRussianResidentialProxyRequired() {
        return true;
    }

    @Override
    public long getPacingDelayMs() {
        return 2200L;
    }

    @Override
    public String getAuthVerificationSelector() {
        return ".user-info__avatar, .header-user-menu, a[href*='/resumes']";
    }

    @Override
    public String getResumeBoostSelector() {
        return "button.resume-up-button, a[href*='action=up']";
    }

    @Override
    public String getResumesPageUrl() {
        return "https://career.habr.ru/resumes";
    }
}
