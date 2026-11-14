package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Map;

/**
 * Domain interface representing a career profession track (taxonomy, skill vectors,
 * and interview rubric).
 */
public interface CareerTrack {

    /**
     * Unique uppercase code (e.g. "BACKEND_JAVA", "DATA_SCIENCE", "FRONTEND", "DEVOPS").
     */
    String getCode();

    /**
     * Canonical hyphenated slug (e.g. "backend-java", "data-science", "frontend").
     */
    String getSlug();

    /**
     * Display name (e.g. "Java Backend Developer").
     */
    String getDisplayName();

    /**
     * Collection name in Qdrant vector database.
     */
    String getVectorCollectionName();

    /**
     * Vector embedding dimension (e.g. 1536 for OpenAI / 768 for BERT / 384 for MiniLM).
     */
    int getVectorDimension();

    /**
     * Mandatory hard skill keywords for semantic matching.
     */
    List<String> getCoreSkillKeywords();

    /**
     * Tooling and ecosystem technologies (frameworks, databases, CI/CD).
     */
    List<String> getToolingKeywords();

    /**
     * Stop keywords that disqualify candidates from this track.
     */
    List<String> getStopKeywords();

    /**
     * System prompt template for the AI interviewer conducting mock interviews.
     */
    String getInterviewSystemPrompt();

    /**
     * Salary brackets by seniority level (Gross RUB/month).
     */
    Map<String, Integer> getSalaryBenchmarks();
}
