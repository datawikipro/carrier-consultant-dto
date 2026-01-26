package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for social network channels/chats.
 * Used for communication between social_network_gateway and other services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialChannelDto {
    @JsonProperty("channelId")
    @JsonAlias("channel_id")
    private String channelId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type; // "dm" | "group" | "channel"

    @JsonProperty("networkType")
    @JsonAlias("network_type")
    private String networkType;
}
