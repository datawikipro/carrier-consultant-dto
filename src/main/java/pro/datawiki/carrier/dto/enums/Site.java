package pro.datawiki.carrier.dto.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum Site {
    HHRU("hh.ru"),
    HIRIFYME("hirify.me"),
    LINKEDIN("linkedin.com"),
    HABR("career.habr.ru"),
    SUPERJOB("superjob.ru"),
    TELEGRAM("telegram");

    @Getter(onMethod_ = @JsonValue)
    private final String value;

    Site(String value) {
        this.value = value;
    }

    @JsonCreator
    public static Site fromValue(String text) {
        if (text == null)
            return null;
        for (Site b : Site.values()) {
            if (b.value.equalsIgnoreCase(text) || b.name().equalsIgnoreCase(text)) {
                return b;
            }
        }
        // Special mappings for common abbreviations
        if (text.equalsIgnoreCase("hh"))
            return HHRU;
        if (text.equalsIgnoreCase("hirify"))
            return HIRIFYME;
        if (text.equalsIgnoreCase("linkedin"))
            return LINKEDIN;
        if (text.equalsIgnoreCase("telegram"))
            return TELEGRAM;
        if (text.equalsIgnoreCase("habr"))
            return HABR;


        return null;
    }
}
