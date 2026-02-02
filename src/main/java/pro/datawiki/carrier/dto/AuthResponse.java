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
public class AuthResponse implements Serializable {
    private String status;
    private String message;

    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    public static AuthResponse success(String sessionId) {
        return AuthResponse.builder()
                .status("SUCCESS")
                .sessionId(sessionId)
                .build();
    }

    public static AuthResponse error(String message) {
        return AuthResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
