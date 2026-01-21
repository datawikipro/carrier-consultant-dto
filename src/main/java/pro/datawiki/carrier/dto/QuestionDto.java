package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DTO для вопроса в анкете работодателя.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDto {

    private Long id;

    /**
     * Порядковый номер вопроса (1, 2, 3...)
     */
    private Integer orderIndex;

    /**
     * Текст вопроса
     */
    private String questionText;

    /**
     * Тип вопроса: TEXT, SCALE, CHOICE
     */
    private String questionType;

    /**
     * Варианты ответа (для типа CHOICE)
     */
    private List<String> options;

    /**
     * Ответ пользователя (опционально)
     */
    private String answer;
}
