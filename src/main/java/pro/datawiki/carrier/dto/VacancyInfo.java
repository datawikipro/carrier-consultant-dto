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

    @JsonProperty("descriptionHtml")
    @JsonAlias("description_html")
    private String descriptionHtml;

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

    @JsonProperty("parserClassName")
    @JsonAlias("parser_class_name")
    private String parserClassName;

    @JsonProperty("requiredSkills")
    @JsonAlias("required_skills")
    private java.util.List<String> requiredSkills;

    @JsonProperty("tags")
    private java.util.List<String> tags;

    public VacancyInfo copyWithStatus(VacancyStatus newStatus) {
        VacancyInfo copy = new VacancyInfo();
        copy.setId(this.id);
        copy.setStatus(newStatus);
        copy.setSourceId(this.sourceId);
        copy.setExternalId(this.externalId);
        copy.setTitle(this.title);
        copy.setCompanyName(this.companyName);
        copy.setCompanyUrl(this.companyUrl);
        copy.setUrl(this.url);
        copy.setSalaryFrom(this.salaryFrom);
        copy.setSalaryTo(this.salaryTo);
        copy.setLocation(this.location);
        copy.setDescription(this.description);
        copy.setDescriptionHtml(this.descriptionHtml);
        copy.setCompanyRating(this.companyRating);
        copy.setApplicationsCount(this.applicationsCount);
        copy.setRawText(this.rawText);
        copy.setMetaProfession(this.metaProfession);
        copy.setMetaProfessionId(this.metaProfessionId);
        copy.setMetaProfessionTitle(this.metaProfessionTitle);
        copy.setTelegramTopicId(this.telegramTopicId);
        copy.setRawTelegramMessage(this.rawTelegramMessage);
        copy.setDownloadedPages(this.downloadedPages != null ? new java.util.HashMap<>(this.downloadedPages) : null);
        copy.setRequirements(this.requirements);
        copy.setResponsibilities(this.responsibilities);
        copy.setConditions(this.conditions);
        copy.setRemains(this.remains);
        copy.setContacts(this.contacts);
        copy.setAboutCompany(this.aboutCompany);
        copy.setStructuredContacts(this.structuredContacts != null ? new java.util.ArrayList<>(this.structuredContacts) : null);
        copy.setCreatedAt(this.createdAt);
        copy.setMacroRegionIds(this.macroRegionIds != null ? new java.util.ArrayList<>(this.macroRegionIds) : null);
        copy.setVacancyCountries(this.vacancyCountries != null ? new java.util.ArrayList<>(this.vacancyCountries) : null);
        copy.setParserClassName(this.parserClassName);
        copy.setRequiredSkills(this.requiredSkills != null ? new java.util.ArrayList<>(this.requiredSkills) : null);
        copy.setTags(this.tags != null ? new java.util.ArrayList<>(this.tags) : null);
        return copy;
    }
}
