package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Public-facing Agent DTO.
 * If isPublic=true: full info returned.
 * If isPublic=false: name masked, resume hidden — only stats visible.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgentDto {
    private Long id;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("is_public")
    private Boolean isPublic;
    @JsonProperty("resume_data_json")
    private String resumeDataJson;
    @JsonProperty("telegram_id")
    private Long telegramId;
    @JsonProperty("bot_type")
    private String botType;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("owner_user_id")
    private Long ownerUserId;
    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("telegram_phone_number")
    private String telegramPhoneNumber;

    @JsonProperty("status")
    private String status;

    @JsonProperty("current_country_id")
    private Long currentCountryId;

    @JsonProperty("current_city")
    private String currentCity;

    @JsonProperty("pdf_file_path")
    private String pdfFilePath;

    @JsonProperty("background_history")
    private String backgroundHistory;

    @JsonProperty("welcome_template")
    private String welcomeTemplate;

    // Statistics (always visible)
    @JsonProperty("application_count")
    private Long applicationCount;
    @JsonProperty("interview_count")
    private Long interviewCount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
