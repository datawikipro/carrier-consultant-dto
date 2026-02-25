package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VacancyCountryDto {
    private Long id;
    private Long countryId;
    private String countryName;
    private String isoCode;

    /** OFFICE or REMOTE */
    private WorkType workType;

    /** Optional city within the country */
    private String city;

    public enum WorkType {
        OFFICE, REMOTE
    }
}
