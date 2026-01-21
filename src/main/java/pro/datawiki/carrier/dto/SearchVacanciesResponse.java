package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private List<VacancyInfo> vacancies;
    private Integer count;
    private Integer totalVacancies;
    private Integer totalPages;
    private Integer currentPage;
    private String error;
}
