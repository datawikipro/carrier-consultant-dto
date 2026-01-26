package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for social network posts.
 * Used for communication between social_network_gateway and other services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SocialPostDto {
    @JsonProperty("postId")
    @JsonAlias("post_id")
    private String postId;

    @JsonProperty("channelId")
    @JsonAlias("channel_id")
    private String channelId;

    @JsonProperty("text")
    private String text;

    @JsonProperty("mediaUrls")
    @JsonAlias("media_urls")
    private List<String> mediaUrls;

    @JsonProperty("createdAt")
    @JsonAlias("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("networkType")
    @JsonAlias("network_type")
    private String networkType;
}
