package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("id")
    private Long id;

    /**
     * Порядковый номер вопроса (1, 2, 3...)
     */
    @JsonProperty("orderIndex")
    @JsonAlias("order_index")
    private Integer orderIndex;

    /**
     * Текст вопроса
     */
    @JsonProperty("questionText")
    @JsonAlias("question_text")
    private String questionText;

    /**
     * Тип вопроса: TEXT, SCALE, CHOICE
     */
    @JsonProperty("questionType")
    @JsonAlias("question_type")
    private String questionType;

    /**
     * Варианты ответа (для типа CHOICE)
     */
    @JsonProperty("options")
    private List<String> options;

    /**
     * Ответ пользователя (опционально)
     */
    @JsonProperty("answer")
    private String answer;
}
