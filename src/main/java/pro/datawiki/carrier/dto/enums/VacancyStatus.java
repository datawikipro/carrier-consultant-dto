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
     * Profession identified but no meta-category assigned yet.
     * Awaiting categorization by carrier-consultant-llm-parse.
     */
    PENDING_CATEGORY,

    /**
     * Unstructured vacancy — regex parsers could not extract fields.
     * Awaiting LLM field extraction by carrier-consultant-llm-parse.
     */
    PENDING_LLM,

    /**
     * All necessary actions (e.g., application) are completed for this vacancy.
     */
    COMPLITED,

    /**
     * Vacancy published to Telegram channel.
     */
    PUBLISHED,

    /**
     * Vacancy was skipped during reprocessing (no changes detected).
     */
    SKIPPED
}
