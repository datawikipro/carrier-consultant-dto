package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.ContactType;

/**
 * DTO для создания/обновления контакта
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VacancyContactRequest {
    @JsonProperty("contactType")
    @JsonAlias("contact_type")
    private ContactType contactType;

    @JsonProperty("contactValue")
    @JsonAlias("contact_value")
    private String contactValue;

    @JsonProperty("contactName")
    @JsonAlias("contact_name")
    private String contactName;

    @JsonProperty("contactPosition")
    @JsonAlias("contact_position")
    private String contactPosition;

    @JsonProperty("notes")
    @JsonAlias("notes")
    private String notes;
}
