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
public class ProfessionListResponseDto {
    @JsonProperty("items")
    @JsonAlias("items")
    private List<ProfessionDto> items;

    @JsonProperty("total")
    @JsonAlias("total")
    private long total;
}
