package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SkillMatchResultDto {

    @JsonProperty("professionId")
    @JsonAlias("profession_id")
    private Long professionId;

    @JsonProperty("matchPercentage")
    @JsonAlias("match_percentage")
    private Double matchPercentage;

    @JsonProperty("matchedSkills")
    @JsonAlias("matched_skills")
    private List<String> matchedSkills;

    @JsonProperty("missingCoreSkills")
    @JsonAlias("missing_core_skills")
    private List<String> missingCoreSkills;

    @JsonProperty("missingSecondarySkills")
    @JsonAlias("missing_secondary_skills")
    private List<String> missingSecondarySkills;

    @JsonProperty("totalMarketSkillsEvaluated")
    @JsonAlias("total_market_skills_evaluated")
    private Integer totalMarketSkillsEvaluated;
}
