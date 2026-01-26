package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingDownloadDto implements Serializable {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("searchQuery")
    @JsonAlias("search_query")
    private String searchQuery;

    @JsonProperty("totalPages")
    @JsonAlias("total_pages")
    private Integer totalPages;

    @JsonProperty("metaProfession")
    @JsonAlias("meta_profession")
    private MetaProfessionDto metaProfession;
}
