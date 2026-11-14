package pro.datawiki.carrier.dto.behavior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.ResumeDto;
import pro.datawiki.carrier.dto.VacancyContactDto;
import pro.datawiki.carrier.dto.enums.Site;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSiteApplyContext {
    private Site site;
    private Long userId;
    private String sessionId;
    private Long vacancyId;
    private String externalVacancyId;
    private String vacancyUrl;
    private String vacancyTitle;
    private String companyName;
    private ResumeDto resume;
    private String coverLetter;
    private List<VacancyContactDto> contacts;
}
