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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployerDetailsDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private Site sourceSite;

    @JsonProperty("name")
    private String name;

    @JsonProperty("location")
    private String location;

    @JsonProperty("logoUrl")
    @JsonAlias("logo_url")
    private String logoUrl;

    @JsonProperty("rating")
    private Double rating;

    @JsonProperty("reviewsCount")
    @JsonAlias("reviews_count")
    private Integer reviewsCount;

    @JsonProperty("isTrusted")
    @JsonAlias("is_trusted")
    private Boolean isTrusted;

    @JsonProperty("isVerified")
    @JsonAlias("is_verified")
    private Boolean isVerified;

    @JsonProperty("description")
    private String description;

    @JsonProperty("industry")
    private String industry;

    @JsonProperty("size")
    private String size;

    @JsonProperty("url")
    private String url;

    @JsonProperty("alternateUrl")
    @JsonAlias("alternate_url")
    private String alternateUrl;

    @JsonProperty("additionalUrls")
    @JsonAlias("additional_urls")
    private List<String> additionalUrls;
}
