package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SystemArchitectureTrack implements CareerTrack {

    public static final String TRACK_ID = "system-architecture";
    public static final String DISPLAY_NAME = "System Architecture";
    public static final String QDRANT_COLLECTION = "vacancies_system_architecture";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("System Design", "Distributed Systems", "Highload", "Microservices", "Clean Architecture", "DDD", "Cloud Architecture", "Event Streaming", "REST API", "gRPC"),
            List.of("C4 Model", "UML", "ADR", "Docker", "Kubernetes", "Kafka", "PostgreSQL", "Linux", "AWS", "GCP", "Azure"),
            List.of("Technical Leadership", "Mentoring", "Architecture Governance", "Stakeholder Management", "Code Review"),
            List.of("No architectural experience", "Manual QA background only", "No distributed systems knowledge")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("System Design", "Distributed Systems", "C4 Model", "Highload", "Cloud Infrastructure"),
                    List.of("Designs robust component boundaries", "Writes clear ADRs", "Troubleshoots complex production bottlenecks")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("Enterprise Architecture", "Technical Leadership", "Cross-team Governance", "Scalability Planning"),
                    List.of("Defines multi-year technology roadmaps", "Aligns technical and business goals", "Drives engineering standards")
            ),
            "ARCHITECT", new GradeRubric(
                    "ARCHITECT",
                    84,
                    List.of("Target Architecture", "Platform Strategy", "Zero-downtime Migration", "Cost Optimization"),
                    List.of("Owns end-to-end platform architecture", "Guides domain modeling and API contracts", "Leads mission-critical system design")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты опытный Chief Architect и технический рецензент. Проводи архитектурную оценку и собеседование кандидата по System Design, распределённым системам, протоколам межсервисного взаимодействия, масштабируемости, надёжности и компромиссам (CAP, PACELC).",
            "Ты карьерный AI-коуч. Составь стратегическое резюме и сопроводительное письмо на архитектурную позицию (Solution / Enterprise / System Architect), подчеркивая опыт проектирования высоконагруженных платформ, подготовку ADR/C4 артефактов и техническое лидерство."
    );

    @Override
    public String getTrackId() {
        return TRACK_ID;
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getQdrantCollectionName() {
        return QDRANT_COLLECTION;
    }

    @Override
    public int getVectorDimension() {
        return VECTOR_DIMENSION;
    }

    @Override
    public SkillClusters getSkillClusters() {
        return SKILL_CLUSTERS;
    }

    @Override
    public Map<String, GradeRubric> getGradeRubrics() {
        return RUBRICS;
    }

    @Override
    public TrackPromptTemplates getPromptTemplates() {
        return PROMPT_TEMPLATES;
    }

    @Override
    public boolean matches(String professionTitle) {
        if (professionTitle == null) {
            return false;
        }
        String lower = professionTitle.toLowerCase(Locale.ROOT);
        return lower.contains("architect")
                || lower.contains("архитектор")
                || lower.contains("architecture");
    }
}
