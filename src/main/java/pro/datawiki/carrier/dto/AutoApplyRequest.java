package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class AutoApplyRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    private String query;
    private String location;

    @JsonProperty("salaryFrom")
    @JsonAlias("salary_from")
    private Integer salaryFrom;

    @JsonProperty("remoteOnly")
    @JsonAlias("remote_only")
    private boolean remoteOnly;

    @JsonProperty("maxApplications")
    @JsonAlias("max_applications")
    private int maxApplications = 10;
}
