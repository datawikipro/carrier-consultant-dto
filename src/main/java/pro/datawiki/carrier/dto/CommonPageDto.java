package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

/**
 * Generic DTO for paged responses.
 * Replaces org.springframework.data.domain.Page in Feign clients to avoid
 * deserialization issues.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public record CommonPageDto<T>(
        List<T> content,
        long totalElements,
        int totalPages,
        int size,
        int number) implements Serializable {
}
