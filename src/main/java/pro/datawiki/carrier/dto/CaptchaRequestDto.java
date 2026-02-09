package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class CaptchaRequestDto implements Serializable {

    @JsonProperty("request_id")
    private String requestId;

    @JsonProperty("url")
    private String url;

    @JsonProperty("screenshot_base64")
    private String screenshotBase64;

    @JsonProperty("timestamp")
    private String timestamp;

    public CaptchaRequestDto() {
    }

    public CaptchaRequestDto(String requestId, String url, String screenshotBase64, String timestamp) {
        this.requestId = requestId;
        this.url = url;
        this.screenshotBase64 = screenshotBase64;
        this.timestamp = timestamp;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getScreenshotBase64() {
        return screenshotBase64;
    }

    public void setScreenshotBase64(String screenshotBase64) {
        this.screenshotBase64 = screenshotBase64;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static class Builder {
        private String requestId;
        private String url;
        private String screenshotBase64;
        private String timestamp;

        public Builder requestId(String requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder screenshotBase64(String screenshotBase64) {
            this.screenshotBase64 = screenshotBase64;
            return this;
        }

        public Builder timestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public CaptchaRequestDto build() {
            return new CaptchaRequestDto(requestId, url, screenshotBase64, timestamp);
        }
    }
}
