package pro.datawiki.carrier.dto.enums;

public enum JobSiteCapability {
    /**
     * Supports user account authentication (login, password, sessions).
     */
    ACCOUNT_AUTHENTICATION,

    /**
     * Supports submitting an application via the website/platform interface.
     */
    NATIVE_APPLY,

    /**
     * Supports messaging inside the job platform's built-in chats.
     */
    MESSAGING_CHAT,

    /**
     * Supports direct application/outreach via Telegram to recruiter (@username / phone).
     */
    RECRUITER_DIRECT_TELEGRAM,

    /**
     * Supports direct application by sending resume PDF + cover letter to recruiter email.
     */
    RECRUITER_DIRECT_EMAIL,

    /**
     * Requires browser automation (Playwright/Puppeteer/WebClicker).
     */
    BROWSER_AUTOMATION
}
