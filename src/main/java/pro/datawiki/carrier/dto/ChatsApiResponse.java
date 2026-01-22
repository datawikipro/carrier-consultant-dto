package pro.datawiki.carrier.dto;

import java.util.List;

/**
 * Response wrapper for GET /messages/{site}/chats endpoint.
 */
public class ChatsApiResponse {

    private List<ChatSummaryDto> chats;
    private int count;
    private String status;
    private String error;

    public ChatsApiResponse() {
    }

    public List<ChatSummaryDto> getChats() {
        return chats;
    }

    public void setChats(List<ChatSummaryDto> chats) {
        this.chats = chats;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
