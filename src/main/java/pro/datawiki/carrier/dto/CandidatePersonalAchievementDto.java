package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePersonalAchievementDto implements Serializable {
    private Long id;
    private Long resumeId;
    private String companyName;
    private String roleTitle;
    private String period;
    private String situation;
    private String task;
    private String action;
    private String resultMetrics;
    private List<String> technologies;
    private LocalDateTime createdAt;
}
