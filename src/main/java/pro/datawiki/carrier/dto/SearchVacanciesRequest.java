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
public class SearchVacanciesRequest implements Serializable {
    private String site;

    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    private String query;

    private String location;

    @JsonProperty("salaryFrom")
    @JsonAlias("salary_from")
    private Integer salaryFrom;

    @JsonProperty("remoteOnly")
    @JsonAlias("remote_only")
    private boolean remoteOnly;

    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int limit = 20;

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData; // Ensure UserDataDto exists
}
