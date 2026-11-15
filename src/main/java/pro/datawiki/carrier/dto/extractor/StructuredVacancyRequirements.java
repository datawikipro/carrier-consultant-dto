package pro.datawiki.carrier.dto.extractor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Structured vacancy requirements extracted deterministically (without LLM)
 * and enriched for database storage and vector matching.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StructuredVacancyRequirements implements Serializable {

    @Builder.Default
    private List<String> responsibilities = new ArrayList<>();

    @Builder.Default
    private List<String> requirements = new ArrayList<>();

    @Builder.Default
    private List<String> niceToHave = new ArrayList<>();

    @Builder.Default
    private List<String> conditions = new ArrayList<>();

    private String aboutCompany;
    private String remains;

    @Builder.Default
    private Set<String> allExtractedSkills = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> hardSkills = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> tooling = new LinkedHashSet<>();

    @Builder.Default
    private Set<String> softSkills = new LinkedHashSet<>();

    private String careerTrackId;
    private String grade;

    private Double experienceMinYears;
    private Double experienceMaxYears;

    private Integer salaryFrom;
    private Integer salaryTo;
    private String salaryCurrency;
    private Boolean salaryGross;

    private String workFormat;
    @Builder.Default
    private Boolean remoteAvailable = false;
    private String schedule;
    private String employmentType;

    /**
     * Normalized consolidated text for embedding generation (Qdrant).
     */
    public String buildCanonicalEmbeddingText(String title) {
        StringBuilder sb = new StringBuilder();
        if (title != null && !title.isBlank()) {
            sb.append("Должность: ").append(title.trim()).append("\n");
        }
        if (grade != null && !grade.isBlank()) {
            sb.append("Грейд: ").append(grade).append("\n");
        }
        if (!allExtractedSkills.isEmpty()) {
            sb.append("Стек и ключевые навыки: ").append(String.join(", ", allExtractedSkills)).append("\n");
        }
        if (!requirements.isEmpty()) {
            sb.append("Требования:\n");
            for (String req : requirements) {
                sb.append("- ").append(req).append("\n");
            }
        }
        if (!responsibilities.isEmpty()) {
            sb.append("Обязанности:\n");
            for (String resp : responsibilities) {
                sb.append("- ").append(resp).append("\n");
            }
        }
        return sb.toString().trim();
    }
}
