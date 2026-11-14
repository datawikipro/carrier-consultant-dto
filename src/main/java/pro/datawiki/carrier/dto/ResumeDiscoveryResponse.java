package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDiscoveryResponse {
    @JsonProperty("received")
    private int received;

    @JsonProperty("newlyDiscovered")
    @JsonAlias("newly_discovered")
    private int newlyDiscovered;

    @JsonProperty("skipped")
    private int skipped;
}
