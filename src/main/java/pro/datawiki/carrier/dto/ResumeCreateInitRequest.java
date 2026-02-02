package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class ResumeCreateInitRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    private String title;

    @JsonProperty("firstName")
    @JsonAlias("first_name")
    private String firstName;

    @JsonProperty("lastName")
    @JsonAlias("last_name")
    private String lastName;

    private String phone;
}
