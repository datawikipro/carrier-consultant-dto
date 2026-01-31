package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExpectedExperienceDto implements Serializable {
    private String company;
    private String position;
    private String description;
    private String start;
    private String end;
}
