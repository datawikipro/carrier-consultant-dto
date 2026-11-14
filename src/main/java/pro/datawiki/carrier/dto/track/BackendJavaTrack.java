package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Map;

public class BackendJavaTrack implements CareerTrack {

    public static final BackendJavaTrack INSTANCE = new BackendJavaTrack();

    @Override
    public String getCode() {
        return "BACKEND_JAVA";
    }

    @Override
    public String getSlug() {
        return "backend-java";
    }

    @Override
    public String getDisplayName() {
        return "Java Backend Developer";
    }

    @Override
    public String getVectorCollectionName() {
        return "career_skills_backend_java";
    }

    @Override
    public int getVectorDimension() {
        return 1536;
    }

    @Override
    public List<String> getCoreSkillKeywords() {
        return List.of("Java", "Spring Boot", "JVM", "Multithreading", "Concurrency", "SQL", "PostgreSQL", "Kafka");
    }

    @Override
    public List<String> getToolingKeywords() {
        return List.of("Docker", "Kubernetes", "Git", "Maven", "Gradle", "Redis", "Elasticsearch", "Hibernate", "JPA");
    }

    @Override
    public List<String> getStopKeywords() {
        return List.of("1C", "PHP", "WordPress", "Delphi");
    }

    @Override
    public String getInterviewSystemPrompt() {
        return "You are an expert Principal Java Architect conducting a technical interview for a Java Backend role. " +
               "Evaluate concurrency, memory model, Spring internals, distributed systems architecture, and code cleanliness.";
    }

    @Override
    public Map<String, Integer> getSalaryBenchmarks() {
        return Map.of(
            "JUNIOR", 120_000,
            "MIDDLE", 250_000,
            "SENIOR", 420_000,
            "LEAD", 550_000
        );
    }
}
