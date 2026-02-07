package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDetailsDto {
    /**
     * Internal database ID of the vacancy.
     */
    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private Long vacancyId;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("title")
    private String title;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    @JsonProperty("description")
    private String description;

    @JsonProperty("salaryFrom")
    @JsonAlias("salary_from")
    private Integer salaryFrom;

    @JsonProperty("salaryTo")
    @JsonAlias("salary_to")
    private Integer salaryTo;

    @JsonProperty("salaryCurrency")
    @JsonAlias("salary_currency")
    private String salaryCurrency;

    @JsonProperty("experienceRequired")
    @JsonAlias("experience_required")
    private String experienceRequired;

    @JsonProperty("employmentType")
    @JsonAlias("employment_type")
    private String employmentType; // full-time, part-time, project

    @JsonProperty("workFormat")
    @JsonAlias("work_format")
    private String workFormat; // remote, hybrid, office

    @JsonProperty("remoteAvailable")
    @JsonAlias("remote_available")
    private Boolean remoteAvailable;

    @JsonProperty("schedule")
    private String schedule; // 5/2, 2/2, flexible

    @JsonProperty("location")
    private String location;

    @JsonProperty("companyRating")
    @JsonAlias("company_rating")
    private Double companyRating;

    @JsonProperty("companyIsTrusted")
    @JsonAlias("company_is_trusted")
    private Boolean companyIsTrusted;

    @JsonProperty("applicationsCount")
    @JsonAlias("applications_count")
    private Integer applicationsCount;

    @JsonProperty("requiredSkills")
    @JsonAlias("required_skills")
    private List<String> requiredSkills;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;

    @JsonProperty("contacts")
    private List<VacancyContactDto> contacts;

    @JsonProperty("source")
    private String source;

    @JsonProperty("sourceUrl")
    private String sourceUrl;
}
