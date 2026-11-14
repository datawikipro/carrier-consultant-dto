package pro.datawiki.carrier.dto.track;

import java.io.Serializable;
import java.util.List;

public record SkillClusters(
        List<String> hardSkills,
        List<String> tooling,
        List<String> softSkills,
        List<String> stopFactors
) implements Serializable {}
