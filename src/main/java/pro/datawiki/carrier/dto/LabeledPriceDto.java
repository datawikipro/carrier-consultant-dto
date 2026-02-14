package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LabeledPriceDto {
    private String label;
    private Integer amount;
}
