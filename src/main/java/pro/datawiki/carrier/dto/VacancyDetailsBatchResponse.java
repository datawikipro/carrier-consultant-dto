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
public class VacancyDetailsBatchResponse {
    @JsonProperty("vacancies")
    private List<VacancyDetailsDto> vacancies;

    @JsonProperty("failedIds")
    @JsonAlias("failed_ids")
    private List<String> failedIds;

    @JsonProperty("error")
    private String error;
}
