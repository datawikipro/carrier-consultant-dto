package pro.datawiki.carrier.dto.jobsite;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Holy domain contract representing a job board / recruitment site.
 * <p>
 * Encapsulates network pacing, authentication indicators, stealth requirements,
 * DOM selectors, and capabilities for automated candidate workflows.
 * External procedural {@code switch (site)} branching is strictly prohibited.
 */
@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "code"
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = HhRuJobSite.class, name = "HHRU"),
    @JsonSubTypes.Type(value = HabrCareerJobSite.class, name = "HABR"),
    @JsonSubTypes.Type(value = LinkedInJobSite.class, name = "LINKEDIN"),
    @JsonSubTypes.Type(value = SuperJobSite.class, name = "SUPERJOB")
})
public interface JobSite {

    /**
     * Unique uppercase identifier (e.g. "HHRU", "HABR", "LINKEDIN", "SUPERJOB").
     */
    String getCode();

    /**
     * Canonical hyphenated URL slug (e.g. "hh-ru", "habr", "linkedin", "superjob").
     */
    String getSlug();

    /**
     * Human-readable display title (e.g. "HeadHunter (hh.ru)").
     */
    String getDisplayName();

    /**
     * Base HTTP origin URL (e.g. "https://hh.ru").
     */
    String getBaseUrl();

    /**
     * Login / authentication entrypoint URL.
     */
    String getLoginUrl();

    /**
     * Whether requests must egress strictly through clean Russian residential proxy (ru-proxy).
     */
    boolean isRussianResidentialProxyRequired();

    /**
     * Minimum delay (milliseconds) between consecutive browser actions to prevent rate-limiting.
     */
    long getPacingDelayMs();

    /**
     * DOM selector indicating an authenticated candidate session.
     */
    String getAuthVerificationSelector();

    /**
     * DOM selector for the "Поднять резюме в поиске" button.
     */
    String getResumeBoostSelector();

    /**
     * URL path or pattern for candidate's resumes page.
     */
    String getResumesPageUrl();

    /**
     * Whether the portal supports active resume bumping in search index.
     */
    boolean supportsResumeBoost();

    /**
     * Whether the portal supports direct automated vacancy applications.
     */
    boolean supportsDirectApply();

    /**
     * Whether the portal provides candidate-recruiter messaging / chat.
     */
    boolean supportsChat();
}
