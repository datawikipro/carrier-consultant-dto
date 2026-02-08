package pro.datawiki.carrier.dto.enums;

/**
 * Possible statuses for a vacancy.
 */
public enum VacancyStatus {
    /**
     * Initial status after being discovered and saved to the database.
     */
    NEW,

    /**
     * Full vacancy details (description, etc.) have been downloaded and parsed.
     */
    DOWNLOAD,

    /**
     * The vacancy is archived or deleted on the original site.
     */
    CLOSED,

    /**
     * Processing or enrichment failed persistently.
     */
    FAILED,

    /**
     * Vacancy data has been parsed into structured fields.
     */
    PARSED,

    /**
     * All necessary actions (e.g., application) are completed for this vacancy.
     */
    COMPLITED,

    /**
     * Vacancy published to Telegram channel (raw format).
     */
    PUBLISHED_RAW,

    /**
     * Vacancy published to Telegram channel (silver format).
     * This status is set when HR is pinged.
     */
    PUBLISHED_SILVER,

    /**
     * Vacancy published to Telegram channel (gold format).
     * This status is set when HR confirms willingness to interview.
     */
    PUBLISHED_GOLD
}
