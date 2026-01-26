package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("languageCode")
    @JsonAlias("language_code")
    private String languageCode;

    @JsonProperty("title")
    private String title;

    @JsonProperty("professionId")
    @JsonAlias("profession_id")
    private Long professionId;

    @JsonProperty("summary")
    private String summary;

    @JsonProperty("desiredPosition")
    @JsonAlias("desired_position")
    private String desiredPosition;

    @JsonProperty("desiredSalaryMin")
    @JsonAlias("desired_salary_min")
    private Integer desiredSalaryMin;

    @JsonProperty("desiredSalaryMax")
    @JsonAlias("desired_salary_max")
    private Integer desiredSalaryMax;

    @JsonProperty("salaryCurrency")
    @JsonAlias("salary_currency")
    private String salaryCurrency;

    @JsonProperty("location")
    private String location;

    @JsonProperty("relocationReady")
    @JsonAlias("relocation_ready")
    private Boolean relocationReady;

    @JsonProperty("remoteReady")
    @JsonAlias("remote_ready")
    private Boolean remoteReady;

    @JsonProperty("isActive")
    @JsonAlias("is_active")
    private Boolean isActive;

    @JsonProperty("isPrimary")
    @JsonAlias("is_primary")
    private Boolean isPrimary;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonAlias("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("experience")
    private Set<ExperienceDto> experience;

    @JsonProperty("education")
    private Set<EducationDto> education;

    @JsonProperty("skills")
    private Set<SkillDto> skills;

    @JsonProperty("languages")
    private Set<LanguageDto> languages;

    @JsonProperty("status")
    private String status;

    @JsonProperty("statusChangeTime")
    @JsonAlias("status_change_time")
    private LocalDateTime statusChangeTime;
}
