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
public class ApplyResponse implements Serializable {
    private String status;
    private String message;

    @JsonProperty("applicationId")
    @JsonAlias("application_id")
    private String applicationId;

    private QuestionnaireDto questionnaire; // Assumes QuestionnaireDto is in same package

    public static ApplyResponse error(String message) {
        return ApplyResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
