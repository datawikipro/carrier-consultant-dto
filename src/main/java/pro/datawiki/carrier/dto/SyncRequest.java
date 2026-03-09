package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class SyncRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("agentId")
    @JsonAlias("agent_id")
    private String agentId;

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData;

    private List<ResumeDto> resumes; // Using inner static class to match original

    @Data
    public static class ResumeDto implements Serializable {
        private String title;
        private List<ExperienceDto> experience;
    }

    @Data
    public static class ExperienceDto implements Serializable {
        private String company;
        private String position;
    }
}
