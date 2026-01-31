package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest implements Serializable {
    private String site;

    private String login;

    private String password;

    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("accountType")
    @JsonAlias("account_type")
    @Builder.Default
    private String accountType = "applicant";

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData; // Ensure UserDataDto exists in shared lib
}
