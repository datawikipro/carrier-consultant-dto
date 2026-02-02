package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpectedExperienceDto implements Serializable {
    private String company;
    private String position;
    private String description;
    @JsonProperty("startDate")
    @JsonAlias("start_date")
    private LocalDate startDate;

    @JsonProperty("endDate")
    @JsonAlias("end_date")
    private LocalDate endDate;
}
