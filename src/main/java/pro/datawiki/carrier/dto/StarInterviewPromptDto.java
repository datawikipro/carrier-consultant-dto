package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StarInterviewPromptDto implements Serializable {
    private Long resumeId;
    private Long experienceId;
    private String companyName;
    private String roleTitle;
    private String period;
    private String targetedTechnology;
    private String questionTitle;
    private String promptText;
    private String starFrameworkHint;
    private List<String> contextSkills;
}
