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
    @Builder.Default
    @JsonProperty("vacancies")
    private List<VacancyDetailsDto> vacancies = new java.util.ArrayList<>();

    @Builder.Default
    @JsonProperty("failedVacancies")
    @JsonAlias({ "failed_ids", "failedIds" })
    private List<VacancyIdPair> failedVacancies = new java.util.ArrayList<>();

    @JsonProperty("error")
    private String error;
}
