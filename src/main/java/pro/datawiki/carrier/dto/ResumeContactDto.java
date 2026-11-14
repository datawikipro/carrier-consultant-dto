package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeContactDto {
    @JsonProperty("type")
    private String type; // e.g. "EMAIL", "PHONE", "TELEGRAM", "GITHUB", "LINKEDIN"

    @JsonProperty("value")
    private String value;
}
