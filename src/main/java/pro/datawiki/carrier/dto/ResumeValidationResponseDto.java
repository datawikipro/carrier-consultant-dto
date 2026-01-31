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
    private boolean valid;
    private String message;
}
