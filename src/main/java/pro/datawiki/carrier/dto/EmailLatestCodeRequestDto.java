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
public class EmailLatestCodeRequestDto implements Serializable {
    @JsonProperty("site")
    private String site;

    @JsonProperty("email_from")
    private String emailFrom;

    @JsonProperty("subject_contains")
    private String subjectContains;

    @JsonProperty("min_timestamp")
    private Double minTimestamp;

    @JsonProperty("timeout_seconds")
    private Integer timeoutSeconds;

    @JsonProperty("imap_user")
    private String imapUser;

    @JsonProperty("imap_password")
    private String imapPassword;

    @JsonProperty("imap_host")
    private String imapHost;

    @JsonProperty("imap_port")
    private Integer imapPort;
}
