package pro.datawiki.carrier.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class ResumeDto {
    private Long id;
    private String languageCode;
    private String title;
    private Long professionId;
    private String summary;
    private String desiredPosition;
    private Integer desiredSalaryMin;
    private Integer desiredSalaryMax;
    private String salaryCurrency;
    private String location;
    private Boolean relocationReady;
    private Boolean remoteReady;
    private Boolean isActive;
    private Boolean isPrimary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Set<ExperienceDto> experience;
    private Set<EducationDto> education;
    private Set<SkillDto> skills;
    private Set<LanguageDto> languages;
}
