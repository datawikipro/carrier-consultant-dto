package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.VacancyStatus;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyPublicationEvent implements Serializable {
    private VacancyDto vacancy;
    private String medal; // e.g., "🥉"
    private VacancyStatus targetStatus;

    // Meta-profession info for topic creation/routing
    private Long metaProfessionId;
    private String metaProfessionTitle;
    private Integer telegramTopicId;
}
