package pro.datawiki.carrier.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class ExperienceDto {
    private Long id;
    private String company;
    private String position;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
}
