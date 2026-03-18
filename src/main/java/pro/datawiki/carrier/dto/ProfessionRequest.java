package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionRequest {
    @JsonProperty("title")
    @JsonAlias("title")
    private String title;

    @JsonProperty("description")
    @JsonAlias("description")
    private String description;

    @JsonProperty("professionLevelId")
    @JsonAlias("profession_level_id")
    private Integer professionLevelId;

    @JsonProperty("metaProfessionIds")
    @JsonAlias("meta_profession_ids")
    private List<Integer> metaProfessionIds;
}
