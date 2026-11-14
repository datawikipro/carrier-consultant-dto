package pro.datawiki.carrier.dto.track;

import java.io.Serializable;

public record TrackPromptTemplates(
        String interviewPrompt,
        String coverLetterPrompt
) implements Serializable {}
