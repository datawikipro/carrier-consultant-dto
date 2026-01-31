package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyCreateRequest {
    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private String sourceSite;

    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    @JsonProperty("companyUrl")
    @JsonAlias("company_url")
    private String companyUrl;

    @JsonProperty("salaryFrom")
    @JsonAlias("salary_from")
    private Integer salaryFrom;

    @JsonProperty("salaryTo")
    @JsonAlias("salary_to")
    private Integer salaryTo;

    @JsonProperty("location")
    private String location;

    @JsonProperty("description")
    private String description;

    @JsonProperty("applicationsCount")
    @JsonAlias("applications_count")
    private Integer applicationsCount;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;
}
