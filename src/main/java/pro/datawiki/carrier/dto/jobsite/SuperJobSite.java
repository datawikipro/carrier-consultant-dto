package pro.datawiki.carrier.dto.jobsite;

public class SuperJobSite extends AbstractJobSite {

    public static final SuperJobSite INSTANCE = new SuperJobSite();

    @Override
    public String getCode() {
        return "SUPERJOB";
    }

    @Override
    public String getSlug() {
        return "superjob";
    }

    @Override
    public String getDisplayName() {
        return "SuperJob";
    }

    @Override
    public String getBaseUrl() {
        return "https://www.superjob.ru";
    }

    @Override
    public String getLoginUrl() {
        return "https://www.superjob.ru/auth/login/";
    }

    @Override
    public boolean isRussianResidentialProxyRequired() {
        return true;
    }

    @Override
    public long getPacingDelayMs() {
        return 2000L;
    }

    @Override
    public String getAuthVerificationSelector() {
        return "[data-qa='header-user-avatar'], a[href*='/user/resumes/']";
    }

    @Override
    public String getResumeBoostSelector() {
        return "button[data-qa='resume-update-date']";
    }

    @Override
    public String getResumesPageUrl() {
        return "https://www.superjob.ru/user/resumes/";
    }
}
