package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for a chat summary (without messages).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatSummaryDto {
    private String chatId;
    private String vacancyTitle;
    private String vacancyExternalId;
    private String companyName;
    private String status;
    private java.util.List<ChatMessageDto> messages;
}
