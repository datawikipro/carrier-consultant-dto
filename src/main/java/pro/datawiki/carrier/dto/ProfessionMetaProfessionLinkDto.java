package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionMetaProfessionLinkDto implements Serializable {
    @JsonProperty("profession_id")
    private Long professionId;
    @JsonProperty("meta_profession_id")
    private Long metaProfessionId;
    @JsonProperty("meta_profession_title")
    private String metaProfessionTitle;
}
