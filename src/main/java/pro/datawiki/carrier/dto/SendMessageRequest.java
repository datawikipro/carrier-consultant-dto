package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class SendMessageRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("chatId")
    @JsonAlias("chat_id")
    private String chatId;

    private String message;

    @JsonProperty("accountType")
    @JsonAlias("account_type")
    private String accountType;

    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private String vacancyId;
}
