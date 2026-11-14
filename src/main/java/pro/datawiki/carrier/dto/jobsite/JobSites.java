package pro.datawiki.carrier.dto.jobsite;

import java.util.*;

/**
 * Registry and static factory for {@link JobSite} implementations.
 */
public final class JobSites {

    private static final Map<String, JobSite> BY_CODE = new HashMap<>();
    private static final Map<String, JobSite> BY_SLUG = new HashMap<>();
    private static final List<JobSite> ALL = new ArrayList<>();

    static {
        register(HhRuJobSite.INSTANCE);
        register(HabrCareerJobSite.INSTANCE);
        register(LinkedInJobSite.INSTANCE);
        register(SuperJobSite.INSTANCE);
    }

    private JobSites() {}

    private static void register(JobSite site) {
        BY_CODE.put(site.getCode().toUpperCase(), site);
        BY_SLUG.put(site.getSlug().toLowerCase(), site);
        ALL.add(site);
    }

    /**
     * Resolves a {@link JobSite} by uppercase code (e.g. "HHRU", "HABR").
     */
    public static JobSite get(String code) {
        if (code == null) return null;
        return BY_CODE.get(code.trim().toUpperCase());
    }

    /**
     * Resolves a {@link JobSite} by slug (e.g. "hh-ru", "habr", "linkedin").
     */
    public static JobSite fromSlug(String slug) {
        if (slug == null) return null;
        return BY_SLUG.get(slug.trim().toLowerCase());
    }

    /**
     * Returns an unmodifiable list of all registered sites.
     */
    public static List<JobSite> all() {
        return Collections.unmodifiableList(ALL);
    }
}
