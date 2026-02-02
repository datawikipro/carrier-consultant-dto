package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDetailsBatchRequest {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("site")
    private String site;

    @JsonProperty("vacancyIds")
    @JsonAlias("vacancy_ids")
    private List<String> vacancyIds;

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData;
}
