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
public class ExperienceVerificationReportDto implements Serializable {
    private Long id;
    private Long resumeId;
    private Long experienceId;
    private Long employerId;
    private String companyName;
    private String positionTitle;
    private Integer startYear;
    private Integer endYear;
    private Double confidenceScore;
    private List<String> claimedSkills;
    private List<String> verifiedSkills;
    private List<String> unconfirmedSkills;
    private List<String> anomalyFlags;
    private List<String> suggestedInterviewQuestions;
    private String status; // VERIFIED, QUESTIONABLE, FLAGGED, NO_PEER_DATA
    private LocalDateTime createdAt;
}
