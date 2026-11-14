package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeEducationItemDto {
    @JsonProperty("university")
    private String university;

    @JsonProperty("faculty")
    private String faculty;

    @JsonProperty("year")
    private Integer year;
}
