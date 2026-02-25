package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CountryDto {
    private Long id;
    private String name;
    private String nameEn;
    private String isoCode;
    private Long macroRegionId;
    private String macroRegionName;
    private String macroRegionCode;
}
