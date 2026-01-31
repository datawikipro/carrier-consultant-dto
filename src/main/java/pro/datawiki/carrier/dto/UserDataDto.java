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
public class UserDataDto implements Serializable {
    @JsonProperty("hhLogin")
    @JsonAlias({ "hh_login", "email", "login" })
    private String hhLogin;

    @JsonProperty("hhPassword")
    @JsonAlias({ "hh_password", "password" })
    private String hhPassword;
}
