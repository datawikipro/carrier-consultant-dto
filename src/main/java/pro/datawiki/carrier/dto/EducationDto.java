package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EducationDto {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("institution")
    private String institution;

    @JsonProperty("degree")
    private String degree;

    @JsonProperty("fieldOfStudy")
    @JsonAlias("field_of_study")
    private String fieldOfStudy;

    @JsonProperty("startDate")
    @JsonAlias("start_date")
    private LocalDate startDate;

    @JsonProperty("endDate")
    @JsonAlias("end_date")
    private LocalDate endDate;
}
