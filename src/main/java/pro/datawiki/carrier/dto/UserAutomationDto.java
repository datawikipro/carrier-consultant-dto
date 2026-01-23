package pro.datawiki.carrier.dto;

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
public class UserAutomationDto {
    private Long id;
    private String email;
    @JsonProperty("userType")
    private String userType;
    @JsonProperty("hhLogin")
    private String hhLogin;
    @JsonProperty("hhPassword")
    private String hhPassword;
}
