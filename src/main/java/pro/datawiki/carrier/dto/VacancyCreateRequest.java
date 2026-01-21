package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class VacancyCreateRequest {
    private String externalId;
    private String sourceSite;
    private String url;
    private String title;
    private String companyName;
    private String companyUrl;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String location;
    private String description;
}
