package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.VacancyStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyPublicationDto {
    private Long id;
    private Long vacancyId;
    private VacancyStatus status;
    private Integer telegramMessageId;
    private LocalDateTime createdAt;
}
