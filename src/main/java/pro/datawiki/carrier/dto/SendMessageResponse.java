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
public class SendMessageResponse implements Serializable {
    private String status;
    private String message;

    public static SendMessageResponse error(String message) {
        return SendMessageResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
