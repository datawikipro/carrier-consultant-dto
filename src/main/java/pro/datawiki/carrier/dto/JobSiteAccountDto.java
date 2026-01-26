package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobSiteAccountDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("userId")
    @JsonAlias("user_id")
    private Long userId;

    @JsonProperty("site")
    private String site;

    @JsonProperty("login")
    private String login;

    @JsonProperty("password")
    private String password;

    @JsonProperty("isActive")
    @JsonAlias("is_active")
    private Boolean isActive;
}
