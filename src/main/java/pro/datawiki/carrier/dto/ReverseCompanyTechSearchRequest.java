package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReverseCompanyTechSearchRequest implements Serializable {
    private List<String> requiredSkills;
    private Integer minYear;
    @Builder.Default
    private int limit = 20;
}
