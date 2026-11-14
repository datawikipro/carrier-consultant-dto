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
public class ResumeDetailSubmissionDto {

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private Site sourceSite;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("externalUrl")
    @JsonAlias("external_url")
    private String externalUrl;

    @JsonProperty("candidateName")
    @JsonAlias("candidate_name")
    private String candidateName;

    @JsonProperty("desiredPosition")
    @JsonAlias("desired_position")
    private String desiredPosition;

    @JsonProperty("desiredSalary")
    @JsonAlias("desired_salary")
    private Integer desiredSalary;

    @JsonProperty("salaryCurrency")
    @JsonAlias("salary_currency")
    private String salaryCurrency;

    @JsonProperty("experienceYearsTotal")
    @JsonAlias("experience_years_total")
    private Double experienceYearsTotal;

    @JsonProperty("city")
    private String city;

    @JsonProperty("skills")
    private List<String> skills;

    @JsonProperty("experienceItems")
    @JsonAlias("experience_items")
    private List<ResumeExperienceItemDto> experienceItems;

    @JsonProperty("educationItems")
    @JsonAlias("education_items")
    private List<ResumeEducationItemDto> educationItems;

    @JsonProperty("contacts")
    private List<ResumeContactDto> contacts;

    @JsonProperty("rawContent")
    @JsonAlias("raw_content")
    private String rawContent;
}
