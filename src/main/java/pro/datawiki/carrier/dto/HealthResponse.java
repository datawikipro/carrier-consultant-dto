package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HealthResponse implements Serializable {
    private String status;
    private String environment;

    @JsonProperty("activeSessions")
    @JsonAlias("active_sessions")
    private Integer activeSessions;
}
