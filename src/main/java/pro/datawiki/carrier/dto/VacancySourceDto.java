package pro.datawiki.carrier.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancySourceDto {
    private Long id;
    private String name;
    private String url;
    private String sourceType;
    private String details;
    private List<ParsingRuleDto> parsingRules;

    public String getName() {
        return name;
    }
}
