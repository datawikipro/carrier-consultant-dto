package pro.datawiki.carrier.dto.extractor;

import pro.datawiki.carrier.dto.track.CareerTrack;
import pro.datawiki.carrier.dto.track.CareerTrackRegistry;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * High-performance deterministic extractor for vacancy requirements,
 * responsibilities, conditions, technologies, experience, and grade.
 * <p>
 * Operates purely with compiled regex, section analyzers, and an indexed IT skill taxonomy.
 * Zero token cost, sub-millisecond execution, zero LLM hallucinations.
 */
public class DeterministicRequirementsExtractor {

    private record MatchPos(String section, int start, int end) {}

    // HTML / Text cleanup patterns
    private static final Pattern BLOCK_TAG_PATTERN = Pattern.compile("(?ui)</?(?:p|div|h[1-6]|li|ul|ol|tr|blockquote)[^>]*>");
    private static final Pattern BR_TAG_PATTERN = Pattern.compile("(?ui)<br\\s*/?>");
    private static final Pattern INLINE_TAG_PATTERN = Pattern.compile("<[^>]+>");
    private static final Pattern MULTI_NEWLINE_PATTERN = Pattern.compile("(?:\\r?\\n[ \\t]*){2,}");
    private static final Pattern MULTI_SPACE_PATTERN = Pattern.compile("[ \\t]+");
    private static final Pattern BULLET_PREFIX_PATTERN = Pattern.compile("^[ \\t]*[-*•–—\\d+.)]+[ \\t]*");

    // Section header detection patterns
    private static final Pattern SECTION_REQ_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:требования|наши\\s+ожидания|что\\s+мы\\s+ждем[^<\\n]*|что\\s+нужно\\s+знать|кого\\s+мы\\s+ищем|" +
                    "мы\\s+ожидаем|стек\\s+технологий|требуемый\\s+стек|необходимые\\s+навыки|hard\\s+skills|requirements|" +
                    "what\\s+you(?:'ll)?\\s+need|must\\s+have|qualifications|ожидания\\s+от\\s+кандидата|требования\\s+к\\s+кандидату)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_RESP_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:обязанности|чем\\s+предстоит\\s+заниматься|задачи|что\\s+нужно\\s+делать|зона\\s+ответственности|" +
                    "предстоит\\s+делать|responsibilities|what\\s+you(?:'ll)?\\s+do|key\\s+tasks|duties|предстоит|ваши\\s+задачи)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_COND_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:условия|мы\\s+предлагаем|что\\s+мы\\s+предлагаем|бенефиты|плюшки|условия\\s+работы|пакет\\s+бенефитов|" +
                    "что\\s+даем|what\\s+we\\s+offer|benefits|perks|working\\s+conditions|наше\\s+предложение|компенсационный\\s+пакет)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_NICE_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:будет\\s+плюсом|плюсом\\s+будет|желательно|приветствуется|как\\s+преимущество|nice\\s+to\\s+have|" +
                    "plus|good\\s+to\\s+have|bonus|дополнительным\\s+плюсом|будет\\s+здорово|дополнительные\\s+требования)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_ABOUT_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:о\\s+компании|о\\s+нас|кто\\s+мы|о\\s+проекте|о\\s+работодателе|about\\s+company|about\\s+us|who\\s+we\\s+are)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    // Experience patterns
    private static final Pattern EXP_RANGE_PATTERN = Pattern.compile(
            "(?ui)(?:(?:опыт(?:\\s+работы)?|experience)[^0-9\\n]{0,80})?(?:от\\s+)?(\\d+)[^0-9\\n]{1,6}(?:до\\s+)?(\\d+)?\\s*(лет|года?|г\\.?|years?|yrs?)"
    );
    private static final Pattern EXP_PLUS_PATTERN = Pattern.compile(
            "(?ui)(?:(?:опыт(?:\\s+работы)?|experience)[^0-9\\n]{0,80})?(\\d+)\\s*\\+\\s*(?:лет|года?|г\\.?|years?|yrs?)"
    );
    private static final Pattern EXP_NO_PATTERN = Pattern.compile(
            "(?ui)(?:не\\s+требуется|без\\s+опыта|no\\s+experience)"
    );

    // Work format patterns
    private static final Pattern REMOTE_PATTERN = Pattern.compile("(?ui)(?:удаленк|удаленн|remote|дистанционн)");
    private static final Pattern HYBRID_PATTERN = Pattern.compile("(?ui)(?:гибрид|hybrid)");
    private static final Pattern OFFICE_PATTERN = Pattern.compile("(?ui)(?:офис|office)");

    // Taxonomy of canonical skills and their patterns
    public record SkillDefinition(String canonicalName, Pattern pattern, String category) {}

    private static final List<SkillDefinition> SKILL_TAXONOMY = new ArrayList<>();

    static {
        // Core Languages
        addSkill("Java", "\\bjava\\b(?!script)", "HARD");
        addSkill("Kotlin", "\\bkotlin\\b", "HARD");
        addSkill("Scala", "\\bscala\\b", "HARD");
        addSkill("Go", "\\b(?:golang|go)\\b(?!\\s*to)", "HARD");
        addSkill("Python", "\\bpython\\b", "HARD");
        addSkill("C++", "\\bc\\+\\+\\b", "HARD");
        addSkill("C#", "\\bc#|\\bcsharp\\b", "HARD");
        addSkill("TypeScript", "\\btypescript\\b|\\bts\\b", "HARD");
        addSkill("JavaScript", "\\bjavascript\\b|\\bjs\\b", "HARD");
        addSkill("Rust", "\\brust\\b", "HARD");
        addSkill("PHP", "\\bphp\\b", "HARD");
        addSkill("Swift", "\\bswift\\b", "HARD");
        addSkill("Ruby", "\\bruby\\b", "HARD");
        addSkill("SQL", "\\bsql\\b", "HARD");

        // Frameworks & Libraries
        addSkill("Spring Boot", "\\bspring(?:[\\s-]boot)?\\b", "HARD");
        addSkill("Spring Security", "\\bspring\\s+security\\b", "HARD");
        addSkill("Spring Data JPA", "\\bspring\\s+data(?:\\s+jpa)?\\b", "HARD");
        addSkill("Spring Cloud", "\\bspring\\s+cloud\\b", "HARD");
        addSkill("Hibernate", "\\bhibernate\\b", "HARD");
        addSkill("JPA", "\\bjpa\\b", "HARD");
        addSkill("Micronaut", "\\bmicronaut\\b", "HARD");
        addSkill("Quarkus", "\\bquarkus\\b", "HARD");
        addSkill("Django", "\\bdjango\\b", "HARD");
        addSkill("FastAPI", "\\bfastapi\\b", "HARD");
        addSkill("Flask", "\\bflask\\b", "HARD");
        addSkill("NestJS", "\\bnest(?:\\.js|js)?\\b", "HARD");
        addSkill("Express", "\\bexpress(?:\\.js)?\\b", "HARD");
        addSkill("React", "\\breact(?:\\.js)?\\b", "HARD");
        addSkill("Vue.js", "\\bvue(?:\\.js)?\\b", "HARD");
        addSkill("Angular", "\\bangular\\b", "HARD");
        addSkill("Next.js", "\\bnext(?:\\.js)?\\b", "HARD");
        addSkill("Node.js", "\\bnode(?:\\.js)?\\b", "HARD");

        // Databases
        addSkill("PostgreSQL", "\\b(?:postgresql|postgres|pg)\\b", "HARD");
        addSkill("MySQL", "\\bmysql\\b", "HARD");
        addSkill("Oracle", "\\boracle(?:\\s+db)?\\b", "HARD");
        addSkill("MS SQL Server", "\\b(?:mssql|sql\\s+server)\\b", "HARD");
        addSkill("MongoDB", "\\bmongo(?:db)?\\b", "HARD");
        addSkill("Redis", "\\bredis\\b", "HARD");
        addSkill("Cassandra", "\\bcassandra\\b", "HARD");
        addSkill("ClickHouse", "\\bclickhouse\\b", "HARD");
        addSkill("Elasticsearch", "\\b(?:elasticsearch|elastic|opensearch)\\b", "HARD");

        // Streaming & Messaging
        addSkill("Kafka", "\\b(?:apache\\s+)?kafka\\b", "HARD");
        addSkill("RabbitMQ", "\\brabbitmq|\\brabbit\\b", "HARD");
        addSkill("ActiveMQ", "\\bactivemq\\b", "HARD");
        addSkill("Pulsar", "\\b(?:apache\\s+)?pulsar\\b", "HARD");
        addSkill("NATS", "\\bnats\\b", "HARD");

        // DevOps & Cloud
        addSkill("Docker", "\\bdocker(?:-compose)?\\b", "TOOL");
        addSkill("Kubernetes", "\\b(?:kubernetes|k8s)\\b", "TOOL");
        addSkill("Helm", "\\bhelm\\b", "TOOL");
        addSkill("Terraform", "\\bterraform\\b", "TOOL");
        addSkill("Ansible", "\\bansible\\b", "TOOL");
        addSkill("GitLab CI", "\\bgitlab[\\s-]ci\\b", "TOOL");
        addSkill("GitHub Actions", "\\bgithub\\s+actions\\b", "TOOL");
        addSkill("Jenkins", "\\bjenkins\\b", "TOOL");
        addSkill("AWS", "\\b(?:aws|amazon\\s+web\\s+services)\\b", "TOOL");
        addSkill("GCP", "\\b(?:gcp|google\\s+cloud)\\b", "TOOL");
        addSkill("Azure", "\\bazure\\b", "TOOL");
        addSkill("Linux", "\\blinux\\b", "TOOL");
        addSkill("Bash", "\\bbash|\\bshell\\b", "TOOL");
        addSkill("Prometheus", "\\bprometheus\\b", "TOOL");
        addSkill("Grafana", "\\bgrafana\\b", "TOOL");

        // Architecture & Protocols
        addSkill("Microservices", "(?ui)(?:микросервис[а-я]*|microservices?)", "HARD");
        addSkill("REST API", "(?ui)\\brest(?:ful)?(?:\\s+api)?\\b", "HARD");
        addSkill("gRPC", "\\bgrpc\\b", "HARD");
        addSkill("GraphQL", "\\bgraphql\\b", "HARD");
        addSkill("WebSocket", "\\bwebsockets?\\b", "HARD");
        addSkill("Clean Architecture", "(?ui)(?:чистая\\s+архитектура|clean\\s+architecture)", "HARD");
        addSkill("DDD", "\\bddd\\b|domain-driven\\s+design", "HARD");
        addSkill("SOLID", "\\bsolid\\b", "HARD");

        // Testing
        addSkill("JUnit", "\\bjunit(?:\\s*5)?\\b", "TOOL");
        addSkill("Mockito", "\\bmockito\\b", "TOOL");
        addSkill("Testcontainers", "\\btestcontainers\\b", "TOOL");
        addSkill("Playwright", "\\bplaywright\\b", "TOOL");
        addSkill("Selenium", "\\bselenium\\b", "TOOL");

        // Big Data & AI
        addSkill("PyTorch", "\\bpytorch\\b", "HARD");
        addSkill("TensorFlow", "\\btensorflow\\b", "HARD");
        addSkill("Spark", "\\b(?:apache\\s+)?spark\\b", "HARD");
        addSkill("Airflow", "\\b(?:apache\\s+)?airflow\\b", "HARD");
        addSkill("Qdrant", "\\bqdrant\\b", "HARD");

        // Tooling & Soft Skills
        addSkill("Git", "\\bgit\\b(?!lab|hub)", "TOOL");
        addSkill("Maven", "\\bmaven\\b", "TOOL");
        addSkill("Gradle", "\\bgradle\\b", "TOOL");
        addSkill("Code Review", "(?ui)(?:code\\s+review|код-ревью|код\\s+ревью)", "SOFT");
        addSkill("Agile/Scrum", "(?ui)(?:agile|scrum|канбан|kanban)", "SOFT");
        addSkill("System Design", "(?ui)system\\s+design|системн[а-я]+\\s+дизайн", "SOFT");
        addSkill("Mentoring", "(?ui)(?:менторинг|наставничество|mentoring)", "SOFT");
    }

    private static void addSkill(String canonical, String regex, String category) {
        SKILL_TAXONOMY.add(new SkillDefinition(canonical, Pattern.compile(regex, Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE), category));
    }

    /**
     * Deterministically parse raw HTML or Markdown vacancy description into structured sections,
     * extracting requirements, responsibilities, conditions, technologies, experience, and grade.
     */
    public static StructuredVacancyRequirements extract(String rawContent, String vacancyTitle) {
        StructuredVacancyRequirements.StructuredVacancyRequirementsBuilder result = StructuredVacancyRequirements.builder();

        if (rawContent == null || rawContent.isBlank()) {
            return result.careerTrackId(CareerTrackRegistry.resolveTrackForTitle(vacancyTitle).getTrackId()).build();
        }

        String plainText = cleanHtml(rawContent);

        // 1. Split text into recognized sections
        Map<String, String> sections = splitSections(rawContent, plainText);

        String reqText = sections.getOrDefault("REQUIREMENTS", "");
        String respText = sections.getOrDefault("RESPONSIBILITIES", "");
        String condText = sections.getOrDefault("CONDITIONS", "");
        String niceText = sections.getOrDefault("NICE_TO_HAVE", "");
        String aboutText = sections.getOrDefault("ABOUT_COMPANY", "");

        // 2. Parse bullet points
        result.requirements(extractBulletPoints(reqText));
        result.responsibilities(extractBulletPoints(respText));
        result.conditions(extractBulletPoints(condText));
        result.niceToHave(extractBulletPoints(niceText));
        result.aboutCompany(aboutText.isBlank() ? null : aboutText.trim());

        // 3. Extract skills from the entire text and specifically from requirements
        Set<String> allSkills = new LinkedHashSet<>();
        Set<String> hardSkills = new LinkedHashSet<>();
        Set<String> tooling = new LinkedHashSet<>();
        Set<String> softSkills = new LinkedHashSet<>();

        for (SkillDefinition skill : SKILL_TAXONOMY) {
            if (skill.pattern().matcher(plainText).find()) {
                allSkills.add(skill.canonicalName());
                switch (skill.category()) {
                    case "HARD" -> hardSkills.add(skill.canonicalName());
                    case "TOOL" -> tooling.add(skill.canonicalName());
                    case "SOFT" -> softSkills.add(skill.canonicalName());
                }
            }
        }
        result.allExtractedSkills(allSkills);
        result.hardSkills(hardSkills);
        result.tooling(tooling);
        result.softSkills(softSkills);

        // 4. Extract experience
        extractExperience(plainText, result);

        // 5. Detect Work Format
        extractWorkFormat(plainText, result);

        // 6. Detect Career Track
        CareerTrack track = CareerTrackRegistry.resolveTrackForTitle(vacancyTitle);
        result.careerTrackId(track.getTrackId());

        // 7. Detect Grade
        result.grade(detectGrade(vacancyTitle, plainText));

        return result.build();
    }

    private static String cleanHtml(String html) {
        if (html == null) return "";
        String text = BR_TAG_PATTERN.matcher(html).replaceAll("\n");
        text = BLOCK_TAG_PATTERN.matcher(text).replaceAll("\n");
        text = text.replace("&quot;", "\"")
                .replace("&amp;", "&")
                .replace("&nbsp;", " ")
                .replace("&#39;", "'")
                .replace("&gt;", ">")
                .replace("&lt;", "<");
        text = INLINE_TAG_PATTERN.matcher(text).replaceAll(" ");
        String[] lines = text.split("\\r?\\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            String trimmed = MULTI_SPACE_PATTERN.matcher(line).replaceAll(" ").trim();
            if (!trimmed.isEmpty()) {
                if (!sb.isEmpty()) {
                    sb.append("\n");
                }
                sb.append(trimmed);
            }
        }
        return sb.toString();
    }

    private static Map<String, String> splitSections(String rawHtml, String plainText) {
        Map<String, String> sections = new HashMap<>();

        // Locate match indices in plainText
        List<MatchPos> matches = new ArrayList<>();

        findMatches(SECTION_REQ_PATTERN, plainText, "REQUIREMENTS", matches);
        findMatches(SECTION_RESP_PATTERN, plainText, "RESPONSIBILITIES", matches);
        findMatches(SECTION_COND_PATTERN, plainText, "CONDITIONS", matches);
        findMatches(SECTION_NICE_PATTERN, plainText, "NICE_TO_HAVE", matches);
        findMatches(SECTION_ABOUT_PATTERN, plainText, "ABOUT_COMPANY", matches);

        matches.sort(Comparator.comparingInt(MatchPos::start));

        for (int i = 0; i < matches.size(); i++) {
            MatchPos current = matches.get(i);
            int nextStart = (i + 1 < matches.size()) ? matches.get(i + 1).start() : plainText.length();
            String sectionContent = plainText.substring(current.end(), nextStart).trim();
            sections.put(current.section(), sectionContent);
        }

        // If no sections were identified, put all text into REQUIREMENTS as fallback
        if (sections.isEmpty() && !plainText.isBlank()) {
            sections.put("REQUIREMENTS", plainText);
        }

        return sections;
    }

    private static void findMatches(Pattern pattern, String text, String section, List<MatchPos> matches) {
        Matcher m = pattern.matcher(text);
        while (m.find()) {
            matches.add(new MatchPos(section, m.start(), m.end()));
        }
    }

    private static List<String> extractBulletPoints(String text) {
        if (text == null || text.isBlank()) {
            return Collections.emptyList();
        }

        List<String> bullets = new ArrayList<>();
        String[] lines = text.split("\\r?\\n");

        for (String line : lines) {
            String trimmed = line.trim();
            if (trimmed.length() < 5) continue;

            String cleaned = BULLET_PREFIX_PATTERN.matcher(trimmed).replaceFirst("").trim();
            if (!cleaned.isBlank() && cleaned.length() >= 5) {
                bullets.add(cleaned);
            }
        }

        // If no newline bullets found, split by semicolon or period if long text
        if (bullets.isEmpty() && text.contains(";")) {
            for (String part : text.split(";")) {
                String cleaned = BULLET_PREFIX_PATTERN.matcher(part.trim()).replaceFirst("").trim();
                if (cleaned.length() >= 5) {
                    bullets.add(cleaned);
                }
            }
        }

        if (bullets.isEmpty() && !text.isBlank()) {
            bullets.add(text.trim());
        }

        return bullets;
    }

    private static void extractExperience(String text, StructuredVacancyRequirements.StructuredVacancyRequirementsBuilder builder) {
        if (EXP_NO_PATTERN.matcher(text).find()) {
            builder.experienceMinYears(0.0).experienceMaxYears(0.0);
            return;
        }

        Matcher mRange = EXP_RANGE_PATTERN.matcher(text);
        if (mRange.find()) {
            try {
                double min = Double.parseDouble(mRange.group(1));
                builder.experienceMinYears(min);
                if (mRange.group(2) != null) {
                    builder.experienceMaxYears(Double.parseDouble(mRange.group(2)));
                }
                return;
            } catch (Exception ignored) {}
        }

        Matcher mPlus = EXP_PLUS_PATTERN.matcher(text);
        if (mPlus.find()) {
            try {
                double min = Double.parseDouble(mPlus.group(1));
                builder.experienceMinYears(min);
                builder.experienceMaxYears(min + 3.0);
            } catch (Exception ignored) {}
        }
    }

    private static void extractWorkFormat(String text, StructuredVacancyRequirements.StructuredVacancyRequirementsBuilder builder) {
        if (HYBRID_PATTERN.matcher(text).find()) {
            builder.workFormat("hybrid").remoteAvailable(true);
        } else if (REMOTE_PATTERN.matcher(text).find()) {
            builder.workFormat("remote").remoteAvailable(true);
        } else if (OFFICE_PATTERN.matcher(text).find()) {
            builder.workFormat("office").remoteAvailable(false);
        }
    }

    private static String detectGrade(String title, String text) {
        String lowerTitle = (title != null ? title.toLowerCase() : "");
        String lowerText = (text != null ? text.toLowerCase() : "");

        if (lowerTitle.contains("lead") || lowerTitle.contains("лид") || lowerTitle.contains("тимлид") || lowerTitle.contains("head")) {
            return "LEAD";
        }
        if (lowerTitle.contains("senior") || lowerTitle.contains("сеньор") || lowerTitle.contains("старший")) {
            return "SENIOR";
        }
        if (lowerTitle.contains("junior") || lowerTitle.contains("джуниор") || lowerTitle.contains("младший") || lowerTitle.contains("intern") || lowerTitle.contains("стажер")) {
            return "JUNIOR";
        }
        if (lowerTitle.contains("middle") || lowerTitle.contains("мидл")) {
            return "MIDDLE";
        }

        // Fallback to text indicators
        if (lowerText.contains("senior") || lowerText.contains("сеньор") || lowerText.contains("от 5 лет") || lowerText.contains("более 5 лет")) {
            return "SENIOR";
        }
        if (lowerText.contains("middle") || lowerText.contains("мидл") || lowerText.contains("от 2 лет") || lowerText.contains("от 3 лет")) {
            return "MIDDLE";
        }
        if (lowerText.contains("junior") || lowerText.contains("джуниор") || lowerText.contains("без опыта") || lowerText.contains("не требуется")) {
            return "JUNIOR";
        }

        return "MIDDLE"; // Default industry median
    }
}
