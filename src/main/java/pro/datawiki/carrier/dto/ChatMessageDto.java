package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("messageId")
    @JsonAlias("message_id")
    private String messageId;

    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;

    @JsonProperty("senderName")
    @JsonAlias("sender_name")
    private String senderName;

    @JsonProperty("isFromMe")
    @JsonAlias("is_from_me")
    private Boolean isFromMe;

    @JsonProperty("text")
    private String text;

    @JsonProperty("timestamp")
    private String timestamp;

    @JsonProperty("messageType")
    @JsonAlias("message_type")
    private String messageType;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("isRead")
    @JsonAlias("is_read")
    private Boolean isRead;

    @JsonProperty("sentAt")
    @JsonAlias("sent_at")
    private LocalDateTime sentAt;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private LocalDateTime createdAt;
}
