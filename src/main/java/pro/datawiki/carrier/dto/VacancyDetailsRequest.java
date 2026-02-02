package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;

@Data
public class VacancyDetailsRequest implements Serializable {
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;

    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private String vacancyId;

    @JsonProperty("userData")
    @JsonAlias("user_data")
    private UserDataDto userData;
}
