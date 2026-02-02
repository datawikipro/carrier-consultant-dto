package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Response from full sync operation.
 * Contains summary of all sync steps performed.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SyncResponse implements Serializable {

    private String status;
    private String message;
    @JsonProperty("sessionId")
    @JsonAlias("session_id")
    private String sessionId;
    @JsonProperty("authStatus")
    @JsonAlias("auth_status")
    private String authStatus;

    // Vacancy search results
    @JsonProperty("vacanciesFound")
    @JsonAlias("vacancies_found")
    private int vacanciesFound;
    @JsonProperty("vacanciesSaved")
    @JsonAlias("vacancies_saved")
    private int vacanciesSaved;

    // Application results
    @JsonProperty("applicationsCount")
    @JsonAlias("applications_count")
    private int applicationsCount;
    @JsonProperty("applicationsAttempted")
    @JsonAlias("applications_attempted")
    private int applicationsAttempted;
    @JsonProperty("applicationsSuccessful")
    @JsonAlias("applications_successful")
    private int applicationsSuccessful;
    @JsonProperty("applicationsFailed")
    @JsonAlias("applications_failed")
    private int applicationsFailed;
    @JsonProperty("rateLimited")
    @JsonAlias("rate_limited")
    private boolean rateLimited;

    // Chat sync results
    @JsonProperty("chatsFound")
    @JsonAlias("chats_found")
    private int chatsFound;
    @JsonProperty("chatsSaved")
    @JsonAlias("chats_saved")
    private int chatsSaved;
    @JsonProperty("messagesSaved")
    @JsonAlias("messages_saved")
    private int messagesSaved;

    private List<ChatSummaryDto> chats;

    // Resume validation
    @JsonProperty("resumesValidated")
    @JsonAlias("resumes_validated")
    private int resumesValidated;

    private List<ValidationResponse> resumeValidations;

    // Errors
    @Builder.Default
    private List<String> errors = new ArrayList<>();

    public static SyncResponse success(String message) {
        return SyncResponse.builder()
                .status("SUCCESS")
                .message(message)
                .build();
    }

    public static SyncResponse error(String message) {
        return SyncResponse.builder()
                .status("ERROR")
                .message(message)
                .build();
    }

    public void addError(String error) {
        if (this.errors == null) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(error);
    }
}
