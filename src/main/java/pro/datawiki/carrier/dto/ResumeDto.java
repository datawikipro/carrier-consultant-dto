package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
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

    @JsonProperty("metaProfessionId")
    @JsonAlias("meta_profession_id")
    private Long metaProfessionId;

    @JsonProperty("telegramPhoneNumber")
    @JsonAlias("telegram_phone_number")
    private String telegramPhoneNumber;

    /** ID of country where the applicant currently lives */
    @JsonProperty("currentCountryId")
    @JsonAlias("current_country_id")
    private Long currentCountryId;

    /** Display name of currentCountry */
    @JsonProperty("currentCountryName")
    private String currentCountryName;

    /** ISO code of currentCountry */
    @JsonProperty("currentCountryIso")
    private String currentCountryIso;

    /** City of current residence */
    @JsonProperty("currentCity")
    @JsonAlias("current_city")
    private String currentCity;

    /** IDs of countries to apply to */
    @JsonProperty("targetCountryIds")
    @JsonAlias("target_country_ids")
    private List<Long> targetCountryIds;

    /** Full country objects */
    @JsonProperty("targetCountries")
    private Set<CountryDto> targetCountries;

    /** IDs of macro-regions to target */
    @JsonProperty("targetMacroRegionIds")
    @JsonAlias("target_macro_region_ids")
    private List<Long> targetMacroRegionIds;

    /** Full macro-region objects */
    @JsonProperty("targetMacroRegions")
    private Set<MacroRegionDto> targetMacroRegions;

}
