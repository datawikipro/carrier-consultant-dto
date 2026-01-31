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
public class UpsertApplicationRequest {
    @JsonProperty("resumeId")
    @JsonAlias("resume_id")
    private Long resumeId;

    @JsonProperty("vacancyExternalId")
    @JsonAlias("vacancy_external_id")
    private String vacancyExternalId;

    @JsonProperty("jobSiteAccountId")
    @JsonAlias("job_site_account_id")
    private Long jobSiteAccountId;

    @JsonProperty("status")
    @JsonAlias("status")
    private String status;

    @JsonProperty("externalApplicationId")
    @JsonAlias("external_application_id")
    private String externalApplicationId;

    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private Long vacancyId;
}
