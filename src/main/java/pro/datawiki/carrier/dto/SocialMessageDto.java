package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for social network messages.
 * Used for communication between social_network_gateway and other services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialMessageDto {
    @JsonProperty("messageId")
    @JsonAlias("message_id")
    private String messageId;

    @JsonProperty("channelId")
    @JsonAlias("channel_id")
    private String channelId;

    @JsonProperty("senderId")
    @JsonAlias("sender_id")
    private String senderId;

    @JsonProperty("senderName")
    @JsonAlias("sender_name")
    private String senderName;

    @JsonProperty("text")
    private String text;

    @JsonProperty("timestamp")
    private LocalDateTime timestamp;

    @JsonProperty("isFromMe")
    @JsonAlias("is_from_me")
    private boolean isFromMe;

    @JsonProperty("networkType")
    @JsonAlias("network_type")
    private String networkType;
}
