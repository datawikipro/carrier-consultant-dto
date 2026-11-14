package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class BackendJavaTrack implements CareerTrack {

    public static final String TRACK_ID = "backend-java";
    public static final String DISPLAY_NAME = "Backend Java";
    public static final String QDRANT_COLLECTION = "vacancies_backend_java";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("Java", "Spring Boot", "Spring Data JPA", "Hibernate", "PostgreSQL", "REST API", "Docker", "Kafka", "Microservices"),
            List.of("Git", "Maven", "Gradle", "IntelliJ IDEA", "Linux", "JUnit 5", "Mockito", "Testcontainers"),
            List.of("System Design communication", "Code Review", "Problem Solving", "Agile/Scrum", "Mentoring"),
            List.of("No server-side programming experience", "Manual QA background only", "No SQL/database knowledge")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "JUNIOR", new GradeRubric(
                    "JUNIOR",
                    6,
                    List.of("Java Core", "OOP fundamentals", "Basic SQL", "Spring Boot basics", "Git"),
                    List.of("Able to write unit tests", "Understands REST principles", "Implements basic CRUD endpoints")
            ),
            "MIDDLE", new GradeRubric(
                    "MIDDLE",
                    24,
                    List.of("Spring Security", "JPA/Hibernate optimization", "PostgreSQL indexing", "Docker", "Message Brokers (Kafka/RabbitMQ)"),
                    List.of("Designs relational schemas", "Handles transactions and concurrency", "Independent feature delivery")
            ),
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("Highload architecture", "Distributed systems", "Performance profiling", "Microservices decomposition", "Kubernetes"),
                    List.of("Leads architectural decisions", "Mentors mid/junior engineers", "Troubleshoots production incidents")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("Engineering management", "Technical strategy", "Cross-team alignment", "System resilience"),
                    List.of("Defines tech radar", "Builds engineering teams", "Drives complex cross-functional initiatives")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты опытный Java Tech Lead интервьюер. Проводи техническое собеседование кандидата по Java 21, Spring Boot, реляционным БД и архитектуре распределённых систем. Задавай глубокие концептуальные вопросы, оценивай понимание памяти JVM, многопоточности и транзакционных уровней изоляции.",
            "Ты карьерный AI-коуч. Составь убедительное сопроводительное письмо на позицию Java Developer, подчеркивая опыт разработки отказоустойчивых сервисов на Spring Boot, работу с PostgreSQL/Kafka и соблюдение практик чистого кода."
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
        return lower.contains("java")
                || lower.contains("джава")
                || (lower.contains("backend") && !lower.contains("frontend") && !lower.contains("python") && !lower.contains("go"))
                || (lower.contains("бэкенд") && !lower.contains("фронтенд") && !lower.contains("python") && !lower.contains("go"))
                || lower.contains("spring");
    }
}
