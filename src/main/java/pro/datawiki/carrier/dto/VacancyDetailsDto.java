package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyDetailsDto {
    private String externalId;
    private String url;
    private String title;
    private String companyName;
    private String description;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String salaryCurrency;
    private String experienceRequired;
    private String employmentType; // full-time, part-time, project
    private String workFormat; // remote, hybrid, office
    private Boolean remoteAvailable;
    private String schedule; // 5/2, 2/2, flexible
    private String location;
    private Double companyRating;
    private Boolean companyIsTrusted;
    private List<String> requiredSkills;
}
