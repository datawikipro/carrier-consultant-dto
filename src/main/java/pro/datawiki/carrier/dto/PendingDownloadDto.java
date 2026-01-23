package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingDownloadDto implements Serializable {
    private Long id;

    @JsonProperty("searchQuery")
    private String searchQuery;

    @JsonProperty("totalPages")
    private Integer totalPages;

    @JsonProperty("metaProfession")
    private MetaProfessionDto metaProfession;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public MetaProfessionDto getMetaProfession() {
        return metaProfession;
    }

    public void setMetaProfession(MetaProfessionDto metaProfession) {
        this.metaProfession = metaProfession;
    }
}
