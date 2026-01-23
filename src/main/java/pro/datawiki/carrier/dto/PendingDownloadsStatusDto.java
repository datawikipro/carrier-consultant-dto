package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PendingDownloadsStatusDto implements Serializable {

    @JsonProperty("allCompleted")
    private Boolean allCompleted;

    public Boolean getAllCompleted() {
        return allCompleted;
    }

    public void setAllCompleted(Boolean allCompleted) {
        this.allCompleted = allCompleted;
    }
}
