package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response wrapper for searchVacancies API.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchVacanciesResponse {
    @JsonProperty("vacancies")
    private List<VacancyInfo> vacancies;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("totalVacancies")
    @JsonAlias("total_vacancies")
    private Integer totalVacancies;

    @JsonProperty("totalPages")
    @JsonAlias("total_pages")
    private Integer totalPages;

    @JsonProperty("currentPage")
    @JsonAlias("current_page")
    private Integer currentPage;

    @JsonProperty("error")
    private String error;
}
