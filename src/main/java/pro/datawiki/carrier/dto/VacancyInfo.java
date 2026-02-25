package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import pro.datawiki.carrier.dto.enums.VacancyStatus;
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
    @JsonAlias({ "id" })
    private Long id;

    @JsonProperty("status")
    private VacancyStatus status;

    @JsonProperty("sourceId")
    @JsonAlias("source_id")
    private Long sourceId;

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

    @JsonProperty("rawText")
    @JsonAlias("raw_text")
    private String rawText;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;

    @JsonProperty("metaProfessionId")
    @JsonAlias("meta_profession_id")
    private Long metaProfessionId;

    @JsonProperty("metaProfessionTitle")
    @JsonAlias("meta_profession_title")
    private String metaProfessionTitle;

    @JsonProperty("telegramTopicId")
    @JsonAlias("telegram_topic_id")
    private Integer telegramTopicId;

    @JsonProperty("rawTelegramMessage")
    @JsonAlias("raw_telegram_message")
    private String rawTelegramMessage;

    @JsonProperty("downloadedPages")
    @JsonAlias("downloaded_pages")
    private java.util.Map<String, String> downloadedPages;

    @JsonProperty("requirements")
    private String requirements;

    @JsonProperty("responsibilities")
    private String responsibilities;

    @JsonProperty("conditions")
    private String conditions;

    @JsonProperty("remains")
    private String remains;

    @JsonProperty("contacts")
    private String contacts;

    @JsonProperty("aboutCompany")
    private String aboutCompany;

    @JsonProperty("structuredContacts")
    private java.util.List<VacancyContactDto> structuredContacts;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private java.time.LocalDateTime createdAt;

    @JsonProperty("macroRegionIds")
    @JsonAlias("macro_region_ids")
    private java.util.List<Long> macroRegionIds;

    @JsonProperty("vacancyCountries")
    @JsonAlias("vacancy_countries")
    private java.util.List<VacancyCountryDto> vacancyCountries;
}
