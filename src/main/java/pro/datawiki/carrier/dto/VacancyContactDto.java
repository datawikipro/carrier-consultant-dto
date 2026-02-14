package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.ContactType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyContactDto {
    @JsonProperty("type")
    private ContactType type;

    @JsonProperty("value")
    private String value;

    @JsonProperty("name")
    private String name;

    @JsonProperty("position")
    private String position;

    @JsonProperty("id")
    private Long id;

    @JsonProperty("vacancyId")
    private Long vacancyId;

    @JsonProperty("notes")
    private String notes;

    @JsonProperty("createdAt")
    private java.time.LocalDateTime createdAt;

    @JsonProperty("updatedAt")
    private java.time.LocalDateTime updatedAt;

    // Compatibility getters/setters for mapped fields if names differ
    public ContactType getContactType() {
        return type;
    }

    public void setContactType(ContactType type) {
        this.type = type;
    }

    public String getContactValue() {
        return value;
    }

    public void setContactValue(String value) {
        this.value = value;
    }

    public String getContactName() {
        return name;
    }

    public void setContactName(String name) {
        this.name = name;
    }

    public String getContactPosition() {
        return position;
    }

    public void setContactPosition(String position) {
        this.position = position;
    }
}
