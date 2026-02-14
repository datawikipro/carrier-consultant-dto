package pro.datawiki.carrier.dto;

import lombok.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramPostsResponse {
    private String channel;
    private Long channel_id;
    private int count;
    private List<TelegramPost> posts;
}
