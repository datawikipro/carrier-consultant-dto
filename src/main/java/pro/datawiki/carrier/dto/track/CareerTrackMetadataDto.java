package pro.datawiki.carrier.dto.track;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CareerTrackMetadataDto implements Serializable {
    private String trackId;
    private String displayName;
    private String qdrantCollectionName;
    private int vectorDimension;
    private SkillClusters skillClusters;
    private Map<String, GradeRubric> gradeRubrics;
    private TrackPromptTemplates promptTemplates;
}
