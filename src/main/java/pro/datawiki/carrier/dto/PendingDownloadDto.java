package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingDownloadDto {
    private Long id;
    private String url;
    private String status;
    private Integer downloadedPages;
    private Integer totalVacancies;
    private Integer totalPages;
    private Long professionId;
    private Long employerId;
    private String type;
    private String details;
}
