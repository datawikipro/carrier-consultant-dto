package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MetaProfessionDto implements Serializable {
    @JsonProperty("id")
    @JsonAlias("id")
    private Long id;

    @JsonProperty("title")
    @JsonAlias("title")
    private String title;

    @JsonProperty("description")
    @JsonAlias("description")
    private String description;

    @JsonProperty("professionCategoryId")
    @JsonAlias("profession_category_id")
    private Long professionCategoryId;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    @JsonAlias("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("telegramTopicId")
    @JsonAlias("telegram_topic_id")
    private Integer telegramTopicId;
}
