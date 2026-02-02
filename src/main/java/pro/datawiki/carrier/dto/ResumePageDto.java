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
public class ResumePageDto implements Serializable {
    private String title;
    private String summary;
    private List<ExpectedExperienceDto> experience; // Updated from ExperienceItemDto to ExpectedExperienceDto
    private List<String> skills;
    private String education;
}
