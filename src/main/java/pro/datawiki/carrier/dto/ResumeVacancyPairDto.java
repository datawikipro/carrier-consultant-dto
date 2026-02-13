package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO для пары резюме-вакансия с приоритетом подписки.
 * Используется для автоматической отправки откликов.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeVacancyPairDto {

    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private Long vacancyId;

    @JsonProperty("resumeId")
    @JsonAlias("resume_id")
    private Long resumeId;

    @JsonProperty("userId")
    @JsonAlias("user_id")
    private Long userId;

    @JsonProperty("metaProfessionTitle")
    @JsonAlias("meta_profession_title")
    private String metaProfessionTitle;

    @JsonProperty("contactType")
    @JsonAlias("contact_type")
    private String contactType;

    @JsonProperty("contactValue")
    @JsonAlias("contact_value")
    private String contactValue;

    @JsonProperty("contactName")
    @JsonAlias("contact_name")
    private String contactName;

    @JsonProperty("subscriptionPlan")
    @JsonAlias("subscription_plan")
    private String subscriptionPlan;

    @JsonProperty("userFirstName")
    @JsonAlias("user_first_name")
    private String userFirstName;

    @JsonProperty("userLastName")
    @JsonAlias("user_last_name")
    private String userLastName;

    @JsonProperty("userMiddleName")
    @JsonAlias("user_middle_name")
    private String userMiddleName;

    @JsonProperty("vacancyTitle")
    @JsonAlias("vacancy_title")
    private String vacancyTitle;

    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private String sourceSite;

    @JsonProperty("pdfFilePath")
    @JsonAlias("pdf_file_path")
    private String pdfFilePath;
}
