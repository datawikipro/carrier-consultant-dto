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
public class JobSiteAuthContext {
    private Site site;
    private Long userId;
    private String sessionId;
    private String email;
    private String login;
    private String password;
    private String otpCode;
    private String sessionCookies;
    private String imapPassword;
}
