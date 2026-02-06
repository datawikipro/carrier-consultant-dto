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
}
