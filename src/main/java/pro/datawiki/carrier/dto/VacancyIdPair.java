package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Pair of internal vacancyId and external site-specific ID.
 * Used to pass both IDs through the enrichment pipeline.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VacancyIdPair {
    /**
     * Internal database ID of the vacancy.
     */
    @JsonProperty("vacancyId")
    @JsonAlias("vacancy_id")
    private Long vacancyId;

    /**
     * External ID from the source site (e.g., hh.ru vacancy ID).
     */
    @JsonProperty("externalId")
    @JsonAlias("external_id")
    private String externalId;
}
