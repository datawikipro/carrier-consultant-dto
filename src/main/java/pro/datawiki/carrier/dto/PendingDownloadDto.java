package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingDownloadDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("profession")
    private String profession;

    @JsonProperty("area")
    private String area;

    @JsonProperty("status")
    private String status;

    @JsonProperty("searchQuery")
    @JsonAlias("search_query")
    private String searchQuery;

    @JsonProperty("totalPages")
    @JsonAlias("total_pages")
    private Integer totalPages;

    @JsonProperty("totalVacancies")
    @JsonAlias("total_vacancies")
    private Integer totalVacancies;

    @JsonProperty("downloadedPages")
    @JsonAlias("downloaded_pages")
    private Integer downloadedPages;

    @JsonProperty("applicationsCount")
    @JsonAlias("applications_count")
    private Integer applicationsCount;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;
}
