package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionCategorizationDto {
    @JsonProperty("raw_title")
    private String rawTitle;

    @JsonProperty("profession_id")
    private Long professionId;

    @JsonProperty("meta_profession_id")
    private Long metaProfessionId;
}
