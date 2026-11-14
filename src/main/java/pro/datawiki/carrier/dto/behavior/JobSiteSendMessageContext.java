package pro.datawiki.carrier.dto.behavior;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pro.datawiki.carrier.dto.enums.Site;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobSiteSendMessageContext {
    private Site site;
    private Long userId;
    private String sessionId;
    private String chatId;
    private String recipientContact;
    private String message;
    private String email;
    private String password;
}
