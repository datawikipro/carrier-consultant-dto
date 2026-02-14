package pro.datawiki.carrier.dto;

import lombok.Data;
import java.util.List;

@Data
public class BotResumeData {
    private Boolean wizard;
    private Integer age;
    private String birthDate;
    private String expMode;
    private List<ExperienceItemDto> experienceItems;
    private Integer expYears;
    private Integer companiesCount;
    private String education;
    private Boolean courses;
}
