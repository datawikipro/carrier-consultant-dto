package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BotApplicationDto(
                String status,
                String vacancyTitle,
                String companyName,
                String vacancyUrl,
                Long chatId) {
}
