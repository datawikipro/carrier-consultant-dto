package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyDto {
    @JsonProperty("id")
    @JsonAlias("id")
    private Long id;

    @JsonProperty("status")
    private pro.datawiki.carrier.dto.enums.VacancyStatus status;

    @JsonProperty("title")
    @JsonAlias("title")
    private String title;

    @JsonProperty("sourceId")
    @JsonAlias("source_id")
    private Long sourceId;

    @JsonProperty("description")
    @JsonAlias("description")
    private String description;

    @JsonProperty("primaryExternalId")
    @JsonAlias("primary_external_id")
    private String primaryExternalId;

    @JsonProperty("primarySourceSite")
    @JsonAlias("primary_source_site")
    private pro.datawiki.carrier.dto.enums.Site primarySourceSite;

    @JsonProperty("url")
    @JsonAlias("url")
    private String url;

    @JsonProperty("salaryMin")
    @JsonAlias("salary_min")
    private Integer salaryMin;

    @JsonProperty("salaryMax")
    @JsonAlias("salary_max")
    private Integer salaryMax;

    @JsonProperty("salaryCurrency")
    @JsonAlias("salary_currency")
    private String salaryCurrency;

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

    @JsonProperty("experienceRequired")
    @JsonAlias("experience_required")
    private String experienceRequired;

    @JsonProperty("employerId")
    @JsonAlias("employer_id")
    private Long employerId;

    @JsonProperty("employerName")
    @JsonAlias("employer_name")
    private String employerName;

    @JsonProperty("employerLocation")
    @JsonAlias("employer_location")
    private String employerLocation;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    @JsonProperty("companyUrl")
    @JsonAlias("company_url")
    private String companyUrl;

    @JsonProperty("professionId")
    @JsonAlias("profession_id")
    private Long professionId;

    @JsonProperty("professionTitle")
    @JsonAlias("profession_title")
    private String professionTitle;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonAlias("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("applicationStatus")
    @JsonAlias("application_status")
    private String applicationStatus;

    @JsonProperty("salaryFrom")
    @JsonAlias("salary_from")
    private Integer salaryFrom;

    @JsonProperty("salaryTo")
    @JsonAlias("salary_to")
    private Integer salaryTo;

    @JsonProperty("isActive")
    @JsonAlias("is_active")
    private Boolean isActive;

    @JsonProperty("requiredSkills")
    @JsonAlias("required_skills")
    private List<String> requiredSkills;

    @JsonProperty("publishedAt")
    @JsonAlias("published_at")
    private LocalDateTime publishedAt;

    @JsonProperty("hasApplication")
    @JsonAlias("has_application")
    private boolean hasApplication;

    @JsonProperty("applicationId")
    @JsonAlias("application_id")
    private Long applicationId;

    @JsonProperty("applicationDate")
    @JsonAlias("application_date")
    private LocalDateTime applicationDate;

    @JsonProperty("metaProfessionId")
    @JsonAlias("meta_profession_id")
    private Long metaProfessionId;

    @JsonProperty("metaProfessionTitle")
    @JsonAlias("meta_profession_title")
    private String metaProfessionTitle;

    @JsonProperty("telegramTopicId")
    @JsonAlias("telegram_topic_id")
    private Integer telegramTopicId;

    @JsonProperty("requirements")
    private String requirements;

    @JsonProperty("responsibilities")
    private String responsibilities;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("remains")
    private String remains;

    @JsonProperty("aboutCompany")
    private String aboutCompany;

    @JsonProperty("contacts")
    private List<VacancyContactDto> contacts;

    @JsonProperty("vacancyCountries")
    @JsonAlias("vacancy_countries")
    private List<VacancyCountryDto> vacancyCountries;

    @JsonProperty("macroRegionIds")
    @JsonAlias("macro_region_ids")
    private List<Long> macroRegionIds;

    @JsonProperty("macroRegionNames")
    @JsonAlias("macro_region_names")
    private List<String> macroRegionNames;

    // Compatibility setters
    public void setExternalId(String externalId) {
        this.primaryExternalId = externalId;
    }

    public void setSourceSite(pro.datawiki.carrier.dto.enums.Site sourceSite) {
        this.primarySourceSite = sourceSite;
    }
}
