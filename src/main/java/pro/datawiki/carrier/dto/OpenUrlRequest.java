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
public class OpenUrlRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("url")
    @JsonAlias("url")
    private String url;

    @JsonProperty("site")
    @JsonAlias("site")
    private String site;
}
