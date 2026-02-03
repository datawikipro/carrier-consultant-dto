package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenericStatusResponse implements Serializable {
    private String status;
    private String message;

    public static GenericStatusResponse error(String message) {
        return GenericStatusResponse.builder()
                .status("error")
                .message(message)
                .build();
    }

    public static GenericStatusResponse success() {
        return GenericStatusResponse.builder()
                .status("success")
                .build();
    }

    public static GenericStatusResponse success(String message) {
        return GenericStatusResponse.builder()
                .status("success")
                .message(message)
                .build();
    }
}
