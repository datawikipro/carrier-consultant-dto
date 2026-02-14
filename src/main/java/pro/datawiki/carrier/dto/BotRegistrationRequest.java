package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BotRegistrationRequest {
    @JsonProperty("owner_id")
    private Long ownerId;
    private String email;
    private String password;
    @JsonProperty("bot_type")
    private String botType;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("resume_url")
    private String resumeUrl;
    @JsonProperty("resume_data")
    private BotResumeData resumeData;
}
