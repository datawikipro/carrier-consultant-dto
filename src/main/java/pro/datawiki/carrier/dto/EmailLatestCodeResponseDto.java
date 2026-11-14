package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailLatestCodeResponseDto implements Serializable {
    @JsonProperty("found")
    private Boolean found;

    @JsonProperty("code")
    private String code;

    @JsonProperty("timestamp")
    private Instant timestamp;

    @JsonProperty("error")
    private String error;
}
