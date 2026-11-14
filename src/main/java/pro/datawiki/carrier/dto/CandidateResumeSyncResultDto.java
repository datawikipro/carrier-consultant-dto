package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateResumeSyncResultDto {
    @JsonProperty("isValid")
    private boolean isValid;

    @JsonProperty("errors")
    private List<String> errors;

    @JsonProperty("warnings")
    private List<String> warnings;

    @JsonProperty("resumeId")
    private Long resumeId;

    @JsonProperty("completenessScore")
    private double completenessScore;
}
