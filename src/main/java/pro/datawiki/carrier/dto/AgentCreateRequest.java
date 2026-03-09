package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * Request to create a new Agent
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgentCreateRequest {
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("middle_name")
    private String middleName;
    @JsonProperty("is_public")
    private Boolean isPublic = false;
    @JsonProperty("resume_data")
    private Map<String, Object> resumeData;
    @JsonProperty("telegram_id")
    private Long telegramId;
    @JsonProperty("bot_type")
    private String botType;
    @JsonProperty("owner_user_id")
    private Long ownerUserId;
    @JsonProperty("link_user_id")
    private Long linkUserId;

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
}
