package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for vacancy information.
 * Mirrors consistent fields used across services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VacancyInfo {
    @JsonProperty("id")
    @JsonAlias({"id"})
    private Long id;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    @JsonProperty("companyUrl")
    @JsonAlias("company_url")
    private String companyUrl;

    @JsonProperty("url")
    private String url;

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

    @JsonProperty("companyRating")
    @JsonAlias("company_rating")
    private Double companyRating;

    @JsonProperty("applicationsCount")
    @JsonAlias("applications_count")
    private Integer applicationsCount;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;
}
