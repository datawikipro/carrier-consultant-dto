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
public class MessageInfo implements Serializable {
    private String id;
    private String sender;
    private String text;
    private String timestamp;

    @JsonProperty("isRead")
    @JsonAlias("is_read")
    private boolean isRead;
}
