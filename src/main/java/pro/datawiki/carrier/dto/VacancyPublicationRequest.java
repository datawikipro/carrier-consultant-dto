package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.VacancyStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyPublicationRequest {
    private Long vacancyId;
    private VacancyStatus status;
    private Integer telegramMessageId;
}
