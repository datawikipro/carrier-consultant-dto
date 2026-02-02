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
public class ApplyRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private String vacancyId;

    @JsonProperty("coverLetter")
    @JsonAlias("cover_letter")
    private String coverLetter;

    @JsonProperty("resumeId")
    @JsonAlias("resume_id")
    private String resumeId;

    private String site;

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData; // Assumes UserDataDto is in same package
}
