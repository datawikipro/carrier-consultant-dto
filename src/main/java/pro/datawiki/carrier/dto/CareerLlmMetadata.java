package pro.datawiki.carrier.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Career domain metadata component.
 * Encapsulates career, recruitment, vacancy, and resume identifiers outside of the generic LLM Gateway core.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CareerLlmMetadata {

    @Builder.Default
    private String domain = "career";

    @JsonProperty("vacancy_id")
    private String vacancyId;

    @JsonProperty("resume_id")
    private String resumeId;

    @JsonProperty("profession_id")
    private String professionId;

    @JsonProperty("agent_id")
    private String agentId;

    @JsonProperty("candidate_name")
    private String candidateName;

    public Map<String, Object> toMetadataMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("domain", domain);
        if (vacancyId != null) map.put("vacancy_id", vacancyId);
        if (resumeId != null) map.put("resume_id", resumeId);
        if (professionId != null) map.put("profession_id", professionId);
        if (agentId != null) map.put("agent_id", agentId);
        if (candidateName != null) map.put("candidate_name", candidateName);
        return map;
    }

    public static CareerLlmMetadata fromMap(Map<String, Object> map) {
        if (map == null) return null;
        return CareerLlmMetadata.builder()
                .domain((String) map.getOrDefault("domain", "career"))
                .vacancyId((String) map.get("vacancy_id"))
                .resumeId((String) map.get("resume_id"))
                .professionId((String) map.get("profession_id"))
                .agentId((String) map.get("agent_id"))
                .candidateName((String) map.get("candidate_name"))
                .build();
    }
}
