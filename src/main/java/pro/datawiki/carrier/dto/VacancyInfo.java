package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for vacancy information.
 * Mirrors consistent fields used across services.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyInfo {
    private String externalId;
    private String title;
    private String companyName;
    private String companyUrl;
    private String url;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String location;
    private String description;
    private Double companyRating;
}
