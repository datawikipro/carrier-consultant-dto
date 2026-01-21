package pro.datawiki.carrier.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for social network posts.
 * Used for communication between social_network_gateway and other services.
 */
public class SocialPostDto {
    private String postId;
    private String channelId;
    private String text;
    private List<String> mediaUrls;
    private LocalDateTime createdAt;
    private String networkType;

    public SocialPostDto() {
    }

    public SocialPostDto(String postId, String channelId, String text, List<String> mediaUrls,
            LocalDateTime createdAt, String networkType) {
        this.postId = postId;
        this.channelId = channelId;
        this.text = text;
        this.mediaUrls = mediaUrls;
        this.createdAt = createdAt;
        this.networkType = networkType;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getMediaUrls() {
        return mediaUrls;
    }

    public void setMediaUrls(List<String> mediaUrls) {
        this.mediaUrls = mediaUrls;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
