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
public class ReverseCompanyTechSearchResponse implements Serializable {

    private List<TargetCompanyMatchDto> matches;
    private int totalFound;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TargetCompanyMatchDto implements Serializable {
        private Long employerId;
        private String companyName;
        private String companyUrl;
        private List<String> matchingSkills;
        private int verifiedEngineersCount;
        private List<Integer> activeYears;
        private List<String> sampleRoles;
        private String matchConfidence; // HIGH, MEDIUM, EMERGING
    }
}
