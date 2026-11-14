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
public class ResumeParseRequestDto {

    @JsonProperty("rawText")
    @JsonAlias("raw_text")
    private String rawText;

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private Site sourceSite;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("externalUrl")
    @JsonAlias("external_url")
    private String externalUrl;

    @JsonProperty("mimeType")
    @JsonAlias("mime_type")
    private String mimeType;
}
