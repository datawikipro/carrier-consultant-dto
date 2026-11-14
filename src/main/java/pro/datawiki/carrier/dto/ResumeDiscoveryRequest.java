package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.Site;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeDiscoveryRequest {

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private Site sourceSite;

    @JsonProperty("items")
    private List<ResumeDiscoveryItem> items;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class ResumeDiscoveryItem {
        @JsonProperty("externalId")
        @JsonAlias("external_id")
        private String externalId;

        @JsonProperty("externalUrl")
        @JsonAlias("external_url")
        private String externalUrl;

        @JsonProperty("desiredPosition")
        @JsonAlias("desired_position")
        private String desiredPosition;

        @JsonProperty("city")
        private String city;
    }
}
