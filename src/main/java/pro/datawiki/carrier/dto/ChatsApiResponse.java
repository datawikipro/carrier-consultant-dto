package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response wrapper for GET /messages/{site}/chats endpoint.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatsApiResponse {

    @JsonProperty("chats")
    private List<ChatSummaryDto> chats;

    @JsonProperty("count")
    private int count;

    @JsonProperty("status")
    private String status;

    @JsonProperty("error")
    private String error;
}
