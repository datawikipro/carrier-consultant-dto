package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyEnrichmentRequest {
    @JsonProperty("description")
    @JsonAlias("description")
    private String description;

    @JsonProperty("experienceRequired")
    @JsonAlias("experience_required")
    private String experienceRequired;

    @JsonProperty("workFormat")
    @JsonAlias("work_format")
    private String workFormat;

    @JsonProperty("schedule")
    @JsonAlias("schedule")
    private String schedule;

    @JsonProperty("employmentType")
    @JsonAlias("employment_type")
    private String employmentType;

    @JsonProperty("location")
    @JsonAlias("location")
    private String location;

    @JsonProperty("remoteAvailable")
    @JsonAlias("remote_available")
    private Boolean remoteAvailable;

    @JsonProperty("requiredSkills")
    @JsonAlias("required_skills")
    private List<String> requiredSkills;

    @JsonProperty("contacts")
    @JsonAlias("contacts")
    private List<VacancyContactDto> contacts;

    @JsonProperty("title")
    @JsonAlias("title")
    private String title;

    @JsonProperty("sourceName")
    @JsonAlias("source_name")
    private String sourceName;

    @JsonProperty("sourceUrl")
    @JsonAlias("source_url")
    private String sourceUrl;
}
