package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class ChatDialogueDto implements Serializable {
    @JsonProperty("chatId")
    @JsonAlias("chat_id")
    private String chatId;

    @JsonProperty("vacancyTitle")
    @JsonAlias("vacancy_title")
    private String vacancyTitle;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    private List<ChatMessageDto> messages; // Assumes ChatMessageDto is in same package
}
