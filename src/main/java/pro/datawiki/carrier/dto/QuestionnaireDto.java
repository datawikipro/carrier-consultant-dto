package pro.datawiki.carrier.dto;

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

    private Long id;

    /**
     * ID вакансии в нашей системе (может быть null если вакансия не сохранена)
     */
    private Long vacancyId;

    /**
     * ID вакансии на job-сайте (hh.ru)
     */
    private String externalVacancyId;

    /**
     * Источник (hh.ru, habr, etc.)
     */
    private String sourceSite;

    /**
     * Описание анкеты от работодателя
     */
    private String description;

    /**
     * Список вопросов
     */
    private List<QuestionDto> questions;

    /**
     * Статус анкеты: PENDING, SKIPPED, ANSWERED
     */
    private String status;

    /**
     * Время обнаружения анкеты
     */
    private LocalDateTime detectedAt;
}
