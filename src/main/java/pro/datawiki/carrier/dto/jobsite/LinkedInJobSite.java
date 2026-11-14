package pro.datawiki.carrier.dto.jobsite;

public class LinkedInJobSite extends AbstractJobSite {

    public static final LinkedInJobSite INSTANCE = new LinkedInJobSite();

    @Override
    public String getCode() {
        return "LINKEDIN";
    }

    @Override
    public String getSlug() {
        return "linkedin";
    }

    @Override
    public String getDisplayName() {
        return "LinkedIn";
    }

    @Override
    public String getBaseUrl() {
        return "https://www.linkedin.com";
    }

    @Override
    public String getLoginUrl() {
        return "https://www.linkedin.com/login";
    }

    @Override
    public boolean isRussianResidentialProxyRequired() {
        return false;
    }

    @Override
    public long getPacingDelayMs() {
        return 3000L;
    }

    @Override
    public String getAuthVerificationSelector() {
        return ".global-nav__me, img.global-nav__me-photo";
    }

    @Override
    public String getResumeBoostSelector() {
        return null; // LinkedIn does not have a single resume bump button
    }

    @Override
    public String getResumesPageUrl() {
        return "https://www.linkedin.com/jobs/";
    }
}
