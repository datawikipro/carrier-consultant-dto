package pro.datawiki.carrier.dto;

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
    private Long id;
    @JsonProperty("userId")
    private Long userId;
    private String site;
    private String login;
    private String password;
    @JsonProperty("isActive")
    private Boolean isActive;
}
