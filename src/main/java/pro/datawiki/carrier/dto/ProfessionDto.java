package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfessionDto implements Serializable {
    private Long id;
    private String title;

    @JsonProperty("metaProfessionIds")
    @JsonAlias("meta_profession_ids")
    private Set<Long> metaProfessionIds;
}
