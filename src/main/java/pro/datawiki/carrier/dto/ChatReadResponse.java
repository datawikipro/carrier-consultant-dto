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
public class ChatReadResponse implements Serializable {
    private String status;
    private String message;
    private List<ChatMessageDto> messages;

    public static ChatReadResponse error(String message) {
        return ChatReadResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }
}
