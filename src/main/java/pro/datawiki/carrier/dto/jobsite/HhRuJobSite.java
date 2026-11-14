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
}
