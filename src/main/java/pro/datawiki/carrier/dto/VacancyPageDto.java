package pro.datawiki.carrier.dto;

import lombok.Data;
import java.util.List;

@Data
public class VacancyPageDto {
    private List<VacancyDto> items;
    private long total;
    private int page;
    private int perPage;
    private int pages;
}
