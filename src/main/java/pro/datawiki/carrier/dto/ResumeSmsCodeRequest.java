package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class ResumeSmsCodeRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    private String code;
}
