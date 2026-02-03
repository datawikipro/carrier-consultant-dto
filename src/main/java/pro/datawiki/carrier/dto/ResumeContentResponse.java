package pro.datawiki.carrier.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResumeContentResponse implements Serializable {
    private String status;
    private ResumePageDto content;
    private String message;

    public static ResumeContentResponse error(String message) {
        return ResumeContentResponse.builder()
                .status("error")
                .message(message)
                .build();
    }

    public static ResumeContentResponse success(ResumePageDto content) {
        return ResumeContentResponse.builder()
                .status("success")
                .content(content)
                .build();
    }
}
