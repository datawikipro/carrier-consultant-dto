package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyFlattenedDto {
    private Long id;
    private String externalId;
    private String title;
    private String companyName;
    private Integer salaryFrom;
    private Integer salaryTo;
    private String salaryCurrency;
    private String experienceRequired;
    private Boolean remoteAvailable;
    private String metaProfessionName;
    private Long metaProfessionId;
    private String level;
    private List<String> skills;
    private List<String> regions;
    private String url;
    private LocalDateTime publishedAt;
}
