package pro.datawiki.carrier.dto.track;

import java.io.Serializable;
import java.util.List;

public record GradeRubric(
        String grade,
        int minExperienceMonths,
        List<String> requiredCompetencies,
        List<String> evaluationCriteria
) implements Serializable {}
