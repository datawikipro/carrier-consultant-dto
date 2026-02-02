package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResponse implements Serializable {
    private String status;
    private String message;
    private List<String> errors;

    public static ValidationResponse valid(String message) {
        return ValidationResponse.builder()
                .status("VALID")
                .message(message)
                .build();
    }

    public static ValidationResponse invalid(String message, List<String> errors) {
        return ValidationResponse.builder()
                .status("INVALID")
                .message(message)
                .errors(errors)
                .build();
    }

    public static ValidationResponse error(String message) {
        return ValidationResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
