package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.Site;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumePendingFetchDto {

    @JsonProperty("resumeId")
    @JsonAlias("resume_id")
    private Long resumeId;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private Site sourceSite;

    @JsonProperty("desiredPosition")
    @JsonAlias("desired_position")
    private String desiredPosition;

    @JsonProperty("url")
    private String url;
}
