package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessagesResponse implements Serializable {
    private String status;
    private List<ChatMessageDto> messages;
    private String error;

    public static ChatMessagesResponse ok(List<ChatMessageDto> messages) {
        return ChatMessagesResponse.builder()
                .status("ok")
                .messages(messages)
                .build();
    }

    public static ChatMessagesResponse empty() {
        return ChatMessagesResponse.builder()
                .status("ok")
                .messages(List.of())
                .build();
    }

    public static ChatMessagesResponse error(String message) {
        return ChatMessagesResponse.builder()
                .status("error")
                .error(message)
                .build();
    }
}
