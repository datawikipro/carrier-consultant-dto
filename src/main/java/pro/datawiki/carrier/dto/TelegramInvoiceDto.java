package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TelegramInvoiceDto {
    @JsonProperty("chat_id")
    private Long chatId;

    private String title;
    private String description;
    private String payload;

    @JsonProperty("provider_token")
    private String providerToken;

    @JsonProperty("start_parameter")
    private String startParameter;

    private String currency;
    private List<LabeledPriceDto> prices;
}
