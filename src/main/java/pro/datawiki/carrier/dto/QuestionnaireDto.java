package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO для анкеты (опросника) работодателя при отклике на вакансию.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionnaireDto {

    @JsonProperty("id")
    private Long id;

    /**
     * ID вакансии в нашей системе (может быть null если вакансия не сохранена)
     */
    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private Long vacancyId;

    /**
     * ID вакансии на job-сайте (hh.ru)
     */
    @JsonProperty("externalVacancyId")
    @JsonAlias("external_vacancy_id")
    private String externalVacancyId;

    /**
     * Источник (hh.ru, habr, etc.)
     */
    @JsonProperty("sourceSite")
    @JsonAlias("source_site")
    private String sourceSite;

    /**
     * Описание анкеты от работодателя
     */
    @JsonProperty("description")
    private String description;

    /**
     * Список вопросов
     */
    @JsonProperty("questions")
    private List<QuestionDto> questions;

    /**
     * Статус анкеты: PENDING, SKIPPED, ANSWERED
     */
    @JsonProperty("status")
    private String status;

    /**
     * Время обнаружения анкеты
     */
    @JsonProperty("detectedAt")
    @JsonAlias("detected_at")
    private LocalDateTime detectedAt;
}
