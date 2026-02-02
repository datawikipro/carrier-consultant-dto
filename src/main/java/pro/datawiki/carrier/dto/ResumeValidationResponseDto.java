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
public class ResumeValidationResponseDto implements Serializable {
    private String status;
    private String message;
    private java.util.List<String> errors;
    private boolean valid; // Keep for backward compat if needed, or remove? I'll keep it but rely on
                           // status.

    public static ResumeValidationResponseDto valid(String message) {
        return ResumeValidationResponseDto.builder()
                .status("VALID")
                .message(message)
                .valid(true)
                .build();
    }

    public static ResumeValidationResponseDto invalid(String message, java.util.List<String> errors) {
        return ResumeValidationResponseDto.builder()
                .status("INVALID")
                .message(message)
                .errors(errors)
                .valid(false)
                .build();
    }

    public static ResumeValidationResponseDto error(String message) {
        return ResumeValidationResponseDto.builder()
                .status("ERROR")
                .message(message)
                .valid(false)
                .build();
    }
}
