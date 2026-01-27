package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAutomationDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("email")
    private String email;

    @JsonProperty("userType")
    @JsonAlias("user_type")
    private String userType;

    @JsonProperty("hhLogin")
    @JsonAlias("hh_login")
    private String hhLogin;

    @JsonProperty("hhPassword")
    @JsonAlias("hh_password")
    private String hhPassword;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private Long metaProfession;
}
