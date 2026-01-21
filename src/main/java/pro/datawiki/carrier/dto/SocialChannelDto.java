package pro.datawiki.carrier.dto;

/**
 * DTO for social network channels/chats.
 * Used for communication between social_network_gateway and other services.
 */
public class SocialChannelDto {
    private String channelId;
    private String name;
    private String type; // "dm" | "group" | "channel"
    private String networkType;

    public SocialChannelDto() {
    }

    public SocialChannelDto(String channelId, String name, String type, String networkType) {
        this.channelId = channelId;
        this.name = name;
        this.type = type;
        this.networkType = networkType;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }
}
