package pro.datawiki.carrier.dto.track;

import java.util.Map;

/**
 * Sacred CareerTrack polymorphic domain contract for CareerLab ecosystem.
 * Defines taxonomy, Qdrant vector space, skill clusters, rubrics, and LLM prompt templates.
 */
public interface CareerTrack {

    String getTrackId();

    String getDisplayName();

    String getQdrantCollectionName();

    int getVectorDimension();

    SkillClusters getSkillClusters();

    Map<String, GradeRubric> getGradeRubrics();

    default GradeRubric getGradeRubric(String grade) {
        if (grade == null) {
            return null;
        }
        return getGradeRubrics().get(grade.toUpperCase());
    }

    TrackPromptTemplates getPromptTemplates();

    boolean matches(String professionTitle);

    default CareerTrackMetadataDto toMetadataDto() {
        return CareerTrackMetadataDto.builder()
                .trackId(getTrackId())
                .displayName(getDisplayName())
                .qdrantCollectionName(getQdrantCollectionName())
                .vectorDimension(getVectorDimension())
                .skillClusters(getSkillClusters())
                .gradeRubrics(getGradeRubrics())
                .promptTemplates(getPromptTemplates())
                .build();
    }
}
