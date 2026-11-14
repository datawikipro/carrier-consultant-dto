package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.SkillTier;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionSkillDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("professionId")
    @JsonAlias("profession_id")
    private Long professionId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("canonicalName")
    @JsonAlias("canonical_name")
    private String canonicalName;

    @JsonProperty("frequency")
    private Double frequency;

    @JsonProperty("vacancyCount")
    @JsonAlias("vacancy_count")
    private Integer vacancyCount;

    @JsonProperty("weight")
    private Double weight;

    @JsonProperty("tier")
    private SkillTier tier;
}
