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
public class ActionResult implements Serializable {
    private String status;
    private String message;
    private Object data;

    public static ActionResult success(String message) {
        return ActionResult.builder()
                .status("SUCCESS")
                .message(message)
                .build();
    }

    public static ActionResult error(String message) {
        return ActionResult.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
