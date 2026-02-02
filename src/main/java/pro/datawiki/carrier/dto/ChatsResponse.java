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
public class ChatsResponse implements Serializable {
    private String status;
    private List<ChatSummaryDto> chats;
    private String error;

    public static ChatsResponse ok(List<ChatSummaryDto> chats) {
        return ChatsResponse.builder()
                .status("ok")
                .chats(chats)
                .build();
    }

    public static ChatsResponse empty() {
        return ChatsResponse.builder()
                .status("ok")
                .chats(List.of())
                .build();
    }

    public static ChatsResponse error(String message) {
        return ChatsResponse.builder()
                .status("error")
                .error(message)
                .build();
    }
}
