package pro.datawiki.carrier.dto;

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
public class EmailVerifyRequestDto implements Serializable {
    @JsonProperty("smtp_host")
    private String smtpHost;

    @JsonProperty("smtp_port")
    private Integer smtpPort;

    private String username;
    private String password;

    @JsonProperty("use_ssl")
    private Boolean useSsl;

    @JsonProperty("to_email")
    private String toEmail;
}
