package pro.datawiki.carrier.dto;

import lombok.*;
import java.util.List;
import pro.datawiki.carrier.dto.enums.SourceType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancySourceDto {
    private Long id;
    private String name;
    private String url;
    private SourceType sourceType;
    private String details;
    private List<ParsingRuleDto> parsingRules;
}
