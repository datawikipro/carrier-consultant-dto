package pro.datawiki.carrier.dto.extractor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.*;

/**
 * Structured candidate profile extracted deterministically from resume
 * and ready for database persistence (Postgres) and vector embedding (Qdrant).
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructuredCandidateProfile implements Serializable {

    private String candidateName;
    private String desiredPosition;
    private String careerTrackId;
    private String grade;

    private Double experienceYearsTotal;
    private Integer desiredSalaryMin;
    private Integer desiredSalaryMax;
    private String salaryCurrency;

    private String city;
    private String country;
    @Builder.Default
    private Boolean remoteReady = false;
    @Builder.Default
    private Boolean relocationReady = false;

    private String summary;

    @Builder.Default
    private Set<String> allSkills = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> hardSkills = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> tooling = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> softSkills = new LinkedHashSet<>();

    @Builder.Default
    private List<StructuredExperienceItem> experiences = new ArrayList<>();

    @Builder.Default
    private List<StructuredEducationItem> educations = new ArrayList<>();

    @Builder.Default
    private Map<String, String> contacts = new LinkedHashMap<>();

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StructuredExperienceItem implements Serializable {
        private String companyName;
        private String position;
        private String department;
        private String city;
        private String period;
        private Integer startYear;
        private Integer startMonth;
        private Integer endYear;
        private Integer endMonth;
        @Builder.Default
        private Boolean isCurrent = false;
        private String description;
        @Builder.Default
        private Set<String> extractedSkills = new LinkedHashSet<>();
    }

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StructuredEducationItem implements Serializable {
        private String institution;
        private String faculty;
        private String specialization;
        private Integer graduationYear;
    }

    /**
     * Build canonical summary for Qdrant semantic matching with vacancies.
     */
    public String buildCanonicalEmbeddingText() {
        StringBuilder sb = new StringBuilder();
        if (desiredPosition != null && !desiredPosition.isBlank()) {
            sb.append("Желаемая должность: ").append(desiredPosition.trim()).append("\n");
        }
        if (grade != null && !grade.isBlank()) {
            sb.append("Грейд: ").append(grade).append("\n");
        }
        if (experienceYearsTotal != null) {
            sb.append("Опыт работы (лет): ").append(experienceYearsTotal).append("\n");
        }
        if (!allSkills.isEmpty()) {
            sb.append("Ключевые навыки: ").append(String.join(", ", allSkills)).append("\n");
        }
        if (summary != null && !summary.isBlank()) {
            sb.append("О себе: ").append(summary.trim()).append("\n");
        }
        if (!experiences.isEmpty()) {
            sb.append("Опыт работы:\n");
            for (StructuredExperienceItem exp : experiences) {
                sb.append("- ").append(exp.getPosition() != null ? exp.getPosition() : "Инженер")
                        .append(" в ").append(exp.getCompanyName() != null ? exp.getCompanyName() : "IT")
                        .append(" (").append(exp.getPeriod() != null ? exp.getPeriod() : "").append("): ")
                        .append(exp.getDescription() != null ? exp.getDescription() : "")
                        .append("\n");
            }
        }
        return sb.toString().trim();
    }
}
