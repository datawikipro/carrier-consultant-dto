package pro.datawiki.carrier.dto;

import java.time.LocalDateTime;

/**
 * DTO for social network messages.
 * Used for communication between social_network_gateway and other services.
 */
public class SocialMessageDto {
    private String messageId;
    private String channelId;
    private String senderId;
    private String senderName;
    private String text;
    private LocalDateTime timestamp;
    private boolean isFromMe;
    private String networkType;

    public SocialMessageDto() {
    }

    public SocialMessageDto(String messageId, String channelId, String senderId, String senderName,
            String text, LocalDateTime timestamp, boolean isFromMe, String networkType) {
        this.messageId = messageId;
        this.channelId = channelId;
        this.senderId = senderId;
        this.senderName = senderName;
        this.text = text;
        this.timestamp = timestamp;
        this.isFromMe = isFromMe;
        this.networkType = networkType;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isFromMe() {
        return isFromMe;
    }

    public void setFromMe(boolean fromMe) {
        isFromMe = fromMe;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
