package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * Unified DTO for a single chat message.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatMessageDto {
    private String messageId;
    private String senderName;
    private Boolean isFromMe;
    private String text;
    private String timestamp;
    private String messageType;

    private Long id;
    private Boolean isRead;
    private LocalDateTime sentAt;
    private LocalDateTime createdAt;
}
