package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record BotChatDto(
        Long chatId,
        String companyName,
        String vacancyTitle,
        Integer totalMessages,
        Integer unreadCount,
        String lastMessagePreview) {
}
