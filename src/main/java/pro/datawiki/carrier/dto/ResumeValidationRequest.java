package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeValidationRequest {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("expectedDescription")
    @JsonAlias("expected_description")
    private String expectedDescription;

    @JsonProperty("expectedExperience")
    @JsonAlias("expected_experience")
    private List<Map<String, Object>> expectedExperience;
}
