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
public class CompanyTechTimelineDto implements Serializable {
    private Long id;
    private Long employerId;
    private String companyName;
    private Integer year;
    private String technology;
    private Integer evidenceCount;
    private List<Long> witnessResumeIds;
    private String consensusStatus; // SINGLE_WITNESS, PEER_VERIFIED, HIGH_CONSENSUS
    private LocalDateTime lastUpdatedAt;
}
