package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for a chat summary (without messages).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatSummaryDto {
    @JsonProperty("chatId")
    @JsonAlias("chat_id")
    private String chatId;

    @JsonProperty("vacancyTitle")
    @JsonAlias("vacancy_title")
    private String vacancyTitle;

    @JsonProperty("vacancyExternalId")
    @JsonAlias("vacancy_external_id")
    private String vacancyExternalId;

    @JsonProperty("companyName")
    @JsonAlias("company_name")
    private String companyName;

    @JsonProperty("status")
    private String status;

    @JsonProperty("hasUnreadMessages")
    @JsonAlias("has_unread_messages")
    private Boolean hasUnreadMessages;

    @JsonProperty("messages")
    private java.util.List<ChatMessageDto> messages;
}
