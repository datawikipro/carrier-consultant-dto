package pro.datawiki.carrier.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class EducationDto {
    private Long id;
    private String institution;
    private String degree;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate endDate;
}
