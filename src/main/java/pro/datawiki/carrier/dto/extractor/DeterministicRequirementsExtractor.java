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
                    "what\\s+we\\s+expect|what\\s+you(?:'ll|\\s+will)?\\s+need|must\\s+have|qualifications|ожидания\\s+от\\s+кандидата|" +
                    "требования\\s+к\\s+кандидату|требования\\s+к\\s+соискателю|требования\\s+к\\s+опыту|навыки\\s+и\\s+опыт|" +
                    "наш\\s+идеальный\\s+кандидат|core\\s+qualifications|candidate\\s+requirements|skills\\s*(?:&|and)\\s*experience|" +
                    "what\\s+you\\s+bring|who\\s+you\\s+are|key\\s+qualifications)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_RESP_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:обязанности|чем\\s+предстоит\\s+заниматься|задачи|что\\s+нужно\\s+делать|зона\\s+ответственности|" +
                    "предстоит\\s+делать|responsibilities|what\\s+you(?:'ll|\\s+will)?\\s*(?:do|work\\s+on|be\\s+doing)|" +
                    "key\\s+tasks|duties|предстоит|ваши\\s+задачи|что\\s+предстоит\\s+делать|основные\\s+задачи|" +
                    "сфера\\s+ответственности|your\\s+role|role\\s+overview|the\\s+role|scope\\s+of\\s+work|key\\s+responsibilities)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_COND_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:условия|мы\\s+предлагаем|что\\s+мы\\s+предлагаем|бенефиты|плюшки|условия\\s+работы|пакет\\s+бенефитов|" +
                    "что\\s+даем|what\\s+we\\s+offer|what\\s+we\\s+provide|benefits|perks|working\\s+conditions|наше\\s+предложение|" +
                    "компенсационный\\s+пакет|условия\\s+сотрудничества|что\\s+мы\\s+готовы\\s+предложить|преимущества\\s+работы\\s+у\\s+нас|" +
                    "what's\\s+in\\s+it\\s+for\\s+you|compensation\\s*(?:&|and)\\s*benefits|why\\s+join\\s+us|what\\s+you\\s+get)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_NICE_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:будет\\s+плюсом|плюсом\\s+будет|желательно|приветствуется|как\\s+преимущество|nice\\s+to\\s+have|" +
                    "(?:would|will)\\s+be\\s+a\\s+plus|plus|good\\s+to\\s+have|bonus|bonus\\s+points|дополнительным\\s+плюсом|" +
                    "будет\\s+здорово|дополнительные\\s+требования|также\\s+приветствуется|преимуществом\\s+будет|" +
                    "preferred\\s+qualifications|desired\\s+skills)" +
                    "[ \\t]*(?:</(?:strong|b)>)?[ \\t]*:?[ \\t]*(?:</(?:p|div|strong|b|h[1-6])>|\\n|$)"
    );

    private static final Pattern SECTION_ABOUT_PATTERN = Pattern.compile(
            "(?ui)(?:^|\\n|<(?:p|div|strong|b|h[1-6])[^>]*>)[ \\t]*(?:<(?:strong|b)>)?[ \\t]*" +
                    "(?:о\\s+компании|о\\s+нас|кто\\s+мы|о\\s+проекте|о\\s+работодателе|о\\s+команде|немного\\s+о\\s+нас|" +
                    "наша\\s+миссия|about\\s+company|about\\s+the\\s+company|about\\s+us|who\\s+we\\s+are|about\\s+the\\s+project|" +
                    "company\\s+overview|about\\s+our\\s+team)" +
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
        addSkill("Go", "\\b(?:golang|go)\\b(?!\\s*to\\b)", "HARD");
        addSkill("Python", "\\bpython\\b", "HARD");
        addSkill("C++", "(?ui)\\bc\\+\\+(?!\\w)|\\bcpp\\b", "HARD");
        addSkill("C#", "(?ui)\\bc#(?!\\w)|\\bcsharp\\b", "HARD");
        addSkill("Rust", "\\brust\\b", "HARD");
        addSkill("TypeScript", "\\btypescript\\b|\\bts\\b", "HARD");
        addSkill("JavaScript", "\\bjavascript\\b|\\bjs\\b", "HARD");
        addSkill("PHP", "\\bphp\\b", "HARD");
        addSkill("Ruby", "\\bruby\\b", "HARD");
        addSkill("Swift", "\\bswift\\b", "HARD");
        addSkill("Objective-C", "\\bobjective-c\\b|\\bobjc\\b", "HARD");
        addSkill("Dart", "\\bdart\\b", "HARD");
        addSkill("Perl", "\\bperl\\b", "HARD");
        addSkill("Groovy", "\\bgroovy\\b", "HARD");
        addSkill("Solidity", "\\bsolidity\\b", "HARD");
        addSkill("SQL", "\\bsql\\b", "HARD");
        addSkill("Bash", "\\bbash\\b|\\bshell\\b", "TOOL");
        addSkill("PowerShell", "\\bpowershell\\b", "TOOL");

        // Backend Frameworks & Runtimes
        addSkill("Spring Boot", "\\bspring(?:[\\s-]boot)?\\b", "HARD");
        addSkill("Spring Security", "\\bspring\\s+security\\b", "HARD");
        addSkill("Spring Data JPA", "\\bspring\\s+data(?:\\s+jpa)?\\b", "HARD");
        addSkill("Spring Cloud", "\\bspring\\s+cloud\\b", "HARD");
        addSkill("Hibernate", "\\bhibernate\\b", "HARD");
        addSkill("JPA", "\\bjpa\\b", "HARD");
        addSkill("Micronaut", "\\bmicronaut\\b", "HARD");
        addSkill("Quarkus", "\\bquarkus\\b", "HARD");
        addSkill("Ktor", "\\bktor\\b", "HARD");
        addSkill("Django", "\\bdjango\\b", "HARD");
        addSkill("FastAPI", "\\bfastapi\\b", "HARD");
        addSkill("Flask", "\\bflask\\b", "HARD");
        addSkill("NestJS", "\\bnest(?:\\.js|js)?\\b", "HARD");
        addSkill("Express", "\\bexpress(?:\\.js)?\\b", "HARD");
        addSkill("Node.js", "\\bnode(?:\\.js)?\\b", "HARD");
        addSkill(".NET", "\\b(?:\\.net|dotnet)(?:\\s+core)?\\b", "HARD");
        addSkill("ASP.NET", "\\basp\\.net(?:\\s+core)?\\b", "HARD");
        addSkill("Ruby on Rails", "\\bruby\\s+on\\s+rails\\b|\\brails\\b", "HARD");
        addSkill("Laravel", "\\blaravel\\b", "HARD");
        addSkill("Axum", "\\baxum\\b", "HARD");
        addSkill("Actix", "\\bactix(?:-web)?\\b", "HARD");
        addSkill("Tokio", "\\btokio\\b", "HARD");
        addSkill("Gin", "\\bgin(?:-gonic)?\\b", "HARD");

        // Frontend, Desktop & Mobile
        addSkill("React", "\\breact(?:\\.js)?\\b", "HARD");
        addSkill("React Native", "\\breact\\s+native\\b", "HARD");
        addSkill("Vue.js", "\\bvue(?:\\.js)?\\b", "HARD");
        addSkill("Nuxt", "\\bnuxt(?:\\.js)?\\b", "HARD");
        addSkill("Angular", "\\bangular\\b", "HARD");
        addSkill("Next.js", "\\bnext(?:\\.js)?\\b", "HARD");
        addSkill("Svelte", "\\bsvelte(?:kit)?\\b", "HARD");
        addSkill("HTML5", "\\bhtml5?\\b", "HARD");
        addSkill("CSS3", "\\bcss3?\\b", "HARD");
        addSkill("Tailwind CSS", "\\btailwind(?:\\s+css)?\\b", "HARD");
        addSkill("Redux", "\\bredux(?:\\s+toolkit)?\\b", "HARD");
        addSkill("Flutter", "\\bflutter\\b", "HARD");
        addSkill("Android SDK", "\\bandroid(?:\\s+sdk)?\\b", "HARD");
        addSkill("iOS SDK", "\\bios(?:\\s+sdk)?\\b", "HARD");
        addSkill("SwiftUI", "\\bswiftui\\b", "HARD");
        addSkill("Jetpack Compose", "\\bjetpack\\s+compose\\b", "HARD");
        addSkill("Windows", "\\bwindows\\b", "HARD");
        addSkill("Win32", "\\bwin32\\b", "HARD");
        addSkill("UWP", "\\buwp\\b|universal\\s+windows\\s+platform", "HARD");
        addSkill("WPF", "\\bwpf\\b", "HARD");

        // AdTech & Media Domain Specific
        addSkill("AdTech", "\\badtech\\b|ad-tech", "HARD");
        addSkill("SSP", "\\bssp\\b|supply-side\\s+platform", "HARD");
        addSkill("DSP", "\\bdsp\\b|demand-side\\s+platform", "HARD");
        addSkill("RTB", "\\brtb\\b|real-time\\s+bidding", "HARD");
        addSkill("OpenRTB", "\\bopenrtb(?:\\s*\\d+(?:\\.\\w+)?)?\\b", "HARD");
        addSkill("Advertising SDK", "\\bad(?:vertising)?\\s+sdk\\b", "HARD");
        addSkill("SDK", "\\bsdk\\b", "HARD");
        addSkill("VAST", "\\bvast\\b", "HARD");
        addSkill("VPAID", "\\bvpaid\\b", "HARD");
        addSkill("MRAID", "\\bmraid\\b", "HARD");
        addSkill("Mediation", "(?ui)\\bmediation\\b|медиаци[а-я]+", "HARD");
        addSkill("Header Bidding", "\\bheader\\s+bidding\\b", "HARD");
        addSkill("Ad Server", "\\bad\\s+server\\b", "HARD");
        addSkill("Ad Fraud Detection", "(?ui)\\bad\\s+fraud\\b|fraud\\s+detection|фрод(?:ов)?", "HARD");
        addSkill("SupplyChain / ads.txt", "\\bads\\.txt\\b|\\bapp-ads\\.txt\\b|\\bsellers\\.json\\b|\\bsupplychain\\b", "HARD");
        addSkill("Floor Pricing", "(?ui)\\bfloor\\s+prices?\\b|флор-прайс[а-я]*", "HARD");
        addSkill("Frequency Capping", "\\bfrequency\\s+cap(?:ping)?\\b", "HARD");

        // Databases (Relational, NoSQL, Vector, Search)
        addSkill("PostgreSQL", "\\b(?:postgresql|postgres|pg)\\b", "HARD");
        addSkill("MySQL", "\\bmysql\\b", "HARD");
        addSkill("MariaDB", "\\bmariadb\\b", "HARD");
        addSkill("Oracle", "\\boracle(?:\\s+db)?\\b", "HARD");
        addSkill("MS SQL Server", "\\b(?:mssql|sql\\s+server)\\b", "HARD");
        addSkill("SQLite", "\\bsqlite\\b", "HARD");
        addSkill("MongoDB", "\\bmongo(?:db)?\\b", "HARD");
        addSkill("Redis", "\\bredis\\b", "HARD");
        addSkill("Memcached", "\\bmemcached\\b", "HARD");
        addSkill("Cassandra", "\\b(?:apache\\s+)?cassandra\\b", "HARD");
        addSkill("ScyllaDB", "\\bscylladb|\\bscylla\\b", "HARD");
        addSkill("ClickHouse", "\\bclickhouse\\b", "HARD");
        addSkill("Couchbase", "\\bcouchbase\\b", "HARD");
        addSkill("Neo4j", "\\bneo4j\\b", "HARD");
        addSkill("Elasticsearch", "\\b(?:elasticsearch|elastic|opensearch)\\b", "HARD");
        addSkill("Solr", "\\b(?:apache\\s+)?solr\\b", "HARD");
        addSkill("Qdrant", "\\bqdrant\\b", "HARD");
        addSkill("Milvus", "\\bmilvus\\b", "HARD");
        addSkill("ChromaDB", "\\bchromadb|\\bchroma\\b", "HARD");
        addSkill("Weaviate", "\\bweaviate\\b", "HARD");
        addSkill("DynamoDB", "\\bdynamodb\\b", "HARD");
        addSkill("CockroachDB", "\\bcockroachdb|\\bcockroach\\b", "HARD");
        addSkill("TiDB", "\\btidb\\b", "HARD");
        addSkill("Snowflake", "\\bsnowflake\\b", "HARD");
        addSkill("BigQuery", "\\bbigquery\\b", "HARD");
        addSkill("Redshift", "\\bredshift\\b", "HARD");
        addSkill("Vertica", "\\bvertica\\b", "HARD");

        // Streaming, Messaging & Integration
        addSkill("Kafka", "\\b(?:apache\\s+)?kafka\\b", "HARD");
        addSkill("Kafka Streams", "\\bkafka\\s+streams?\\b", "HARD");
        addSkill("RabbitMQ", "\\brabbitmq|\\brabbit\\b", "HARD");
        addSkill("ActiveMQ", "\\bactivemq\\b", "HARD");
        addSkill("Pulsar", "\\b(?:apache\\s+)?pulsar\\b", "HARD");
        addSkill("NATS", "\\bnats\\b", "HARD");
        addSkill("AWS SQS", "\\b(?:aws\\s+)?sqs\\b", "HARD");
        addSkill("AWS SNS", "\\b(?:aws\\s+)?sns\\b", "HARD");
        addSkill("ZeroMQ", "\\bzeromq|\\b0mq\\b", "HARD");
        addSkill("Event Streaming", "(?ui)\\bevent\\s+streams?\\b|\\bevent\\s+streaming\\b|потоков[а-я]+\\s+обработк[а-я]+", "HARD");
        addSkill("Event-Driven Architecture", "(?ui)\\bevent-driven\\s+architectures?\\b|\\beda\\b", "HARD");
        addSkill("Flink", "\\b(?:apache\\s+)?flink\\b", "HARD");
        addSkill("Spark", "\\b(?:apache\\s+)?spark\\b", "HARD");
        addSkill("Spark Streaming", "\\bspark\\s+streaming\\b", "HARD");
        addSkill("Debezium", "\\bdebezium\\b", "HARD");
        addSkill("CDC", "\\bcdc\\b|change\\s+data\\s+capture", "HARD");

        // DevOps & Cloud Infrastructure
        addSkill("Docker", "\\bdocker(?:-compose)?\\b", "TOOL");
        addSkill("Podman", "\\bpodman\\b", "TOOL");
        addSkill("Kubernetes", "\\b(?:kubernetes|k8s)\\b", "TOOL");
        addSkill("Helm", "\\bhelm\\b", "TOOL");
        addSkill("Terraform", "\\bterraform\\b", "TOOL");
        addSkill("Ansible", "\\bansible\\b", "TOOL");
        addSkill("Pulumi", "\\bpulumi\\b", "TOOL");
        addSkill("GitLab CI", "\\bgitlab[\\s-]ci\\b", "TOOL");
        addSkill("GitHub Actions", "\\bgithub\\s+actions\\b", "TOOL");
        addSkill("Jenkins", "\\bjenkins\\b", "TOOL");
        addSkill("ArgoCD", "\\bargo(?:cd)?\\b", "TOOL");
        addSkill("FluxCD", "\\bfluxcd|\\bflux\\b", "TOOL");
        addSkill("AWS", "\\b(?:aws|amazon\\s+web\\s+services)\\b", "TOOL");
        addSkill("GCP", "\\b(?:gcp|google\\s+cloud)\\b", "TOOL");
        addSkill("Azure", "\\bazure\\b", "TOOL");
        addSkill("OpenStack", "\\bopenstack\\b", "TOOL");
        addSkill("Linux", "\\blinux\\b", "TOOL");
        addSkill("Prometheus", "\\bprometheus\\b", "TOOL");
        addSkill("Grafana", "\\bgrafana\\b", "TOOL");
        addSkill("ELK Stack", "\\belk(?:\\s+stack)?\\b", "TOOL");
        addSkill("OpenTelemetry", "\\bopentelemetry|\\botel\\b", "TOOL");
        addSkill("Jaeger", "\\bjaeger\\b", "TOOL");
        addSkill("Datadog", "\\bdatadog\\b", "TOOL");
        addSkill("Loki", "\\bgrafana\\s+loki|\\bloki\\b", "TOOL");
        addSkill("Nginx", "\\bnginx\\b", "TOOL");
        addSkill("Envoy", "\\benvoy(?:\\s+proxy)?\\b", "TOOL");
        addSkill("Traefik", "\\btraefik\\b", "TOOL");
        addSkill("Kong", "\\bkong(?:\\s+gateway)?\\b", "TOOL");
        addSkill("Istio", "\\bistio\\b", "TOOL");

        // Architecture, Protocols & Design
        addSkill("Microservices", "(?ui)(?:микросервис[а-я]*|microservices?)", "HARD");
        addSkill("REST API", "(?ui)\\brest(?:ful)?(?:\\s+api)?\\b|\\bapi\\b", "HARD");
        addSkill("OpenAPI", "(?ui)\\bopenapi\\b|\\bswagger\\b", "HARD");
        addSkill("gRPC", "\\bgrpc\\b", "HARD");
        addSkill("Protobuf", "\\bprotobuf|protocol\\s+buffers?\\b", "HARD");
        addSkill("GraphQL", "\\bgraphql\\b", "HARD");
        addSkill("WebSocket", "\\bwebsockets?\\b", "HARD");
        addSkill("WebRTC", "\\bwebrtc\\b", "HARD");
        addSkill("RPC", "\\brpc\\b", "HARD");
        addSkill("Clean Architecture", "(?ui)(?:чистая\\s+архитектура|clean\\s+architecture)", "HARD");
        addSkill("DDD", "(?ui)\\bddd\\b|domain-driven\\s+design", "HARD");
        addSkill("Hexagonal Architecture", "(?ui)hexagonal\\s+architecture|гексагональн[а-я]+\\s+архитектур[а-я]+", "HARD");
        addSkill("CQRS", "\\bcqrs\\b", "HARD");
        addSkill("Event Sourcing", "(?ui)event\\s+sourcing|евент\\s+сорсинг", "HARD");
        addSkill("SOLID", "\\bsolid\\b", "HARD");
        addSkill("Highload", "(?ui)\\bhigh-?load\\b|высоконагруженн[а-я]+", "HARD");
        addSkill("Low Latency", "(?ui)\\blow-?latency\\b|низк[а-я]+\\s+задержк[а-я]+", "HARD");
        addSkill("Distributed Systems", "(?ui)distributed\\s+(?:backend\\s+)?systems?|distributed\\s+(?:backend\\s+)?architectures?|распределенн[а-я]+\\s+систем[а-я]+", "HARD");
        addSkill("Multi-Tenancy", "(?ui)multi-tenan(?:cy|t)|мультитенантн[а-я]+", "HARD");
        addSkill("C4 Model", "\\bc4(?:\\s+model)?\\b", "HARD");
        addSkill("UML", "\\buml\\b", "HARD");
        addSkill("ADR", "(?ui)\\badrs?\\b|architecture\\s+decision\\s+records?", "HARD");
        addSkill("System Design", "(?ui)system\\s+design|системн[а-я]+\\s+дизайн|architectural\\s+design\\b|архитектурн[а-я]+\\s+проектирован[а-я]+", "HARD");
        addSkill("HTTP/HTTPS", "(?ui)\\bhttps?\\b|\\bhttp/https\\b", "HARD");
        addSkill("HTTP/2", "(?ui)\\bhttp/2\\b", "HARD");
        addSkill("HTTP/3", "(?ui)\\bhttp/3\\b", "HARD");
        addSkill("TCP/IP", "(?ui)\\btcp(?:/ip)?\\b", "HARD");
        addSkill("UDP", "(?ui)\\budp\\b", "HARD");
        addSkill("TLS/SSL", "(?ui)\\b(?:tls|ssl)\\b", "HARD");

        // Security, Privacy & Compliance
        addSkill("GDPR", "\\bgdpr\\b", "HARD");
        addSkill("CCPA", "\\bccpa\\b", "HARD");
        addSkill("Consent Management", "(?ui)consent\\s+management|управлени[а-я]+\\s+согласи[а-я]+", "HARD");
        addSkill("Data Privacy", "(?ui)personal\\s+data\\s+protection|data\\s+privacy|защит[а-я]+\\s+персональных\\s+данных", "HARD");
        addSkill("OAuth2", "(?ui)\\boauth(?:2(?:\\.0)?)?\\b", "HARD");
        addSkill("OpenID Connect", "(?ui)\\boidc\\b|openid\\s+connect", "HARD");
        addSkill("JWT", "\\bjwt\\b|json\\s+web\\s+token", "HARD");
        addSkill("SAML", "\\bsaml(?:\\s*2\\.0)?\\b", "HARD");
        addSkill("SSO", "\\bsso\\b|single\\s+sign-on", "HARD");
        addSkill("RBAC", "\\brbac\\b|role-based\\s+access", "HARD");
        addSkill("ABAC", "\\babac\\b|attribute-based\\s+access", "HARD");
        addSkill("Keycloak", "\\bkeycloak\\b", "TOOL");
        addSkill("Vault", "\\bvault\\b(?!\\s*boy)", "TOOL");
        addSkill("mTLS", "\\bmtls\\b|mutual\\s+tls", "HARD");
        addSkill("WAF", "\\bwaf\\b|web\\s+application\\s+firewall", "HARD");
        addSkill("Zero Trust", "(?ui)zero\\s+trust", "HARD");
        addSkill("PCI DSS", "\\bpci[\\s-]dss\\b", "HARD");
        addSkill("SOC2", "\\bsoc\\s*2\\b", "HARD");
        addSkill("HIPAA", "\\bhipaa\\b", "HARD");
        addSkill("Penetration Testing", "(?ui)penetration\\s+testing|pentest", "HARD");
        addSkill("OWASP", "\\bowasp\\b", "HARD");

        // Testing & Quality Assurance
        addSkill("JUnit", "\\bjunit(?:\\s*5)?\\b", "TOOL");
        addSkill("TestNG", "\\btestng\\b", "TOOL");
        addSkill("Mockito", "\\bmockito\\b", "TOOL");
        addSkill("Testcontainers", "\\btestcontainers\\b", "TOOL");
        addSkill("AssertJ", "\\bassertj\\b", "TOOL");
        addSkill("Pytest", "\\bpytest\\b", "TOOL");
        addSkill("Jest", "\\bjest\\b", "TOOL");
        addSkill("Mocha", "\\bmocha\\b", "TOOL");
        addSkill("Cypress", "\\bcypress\\b", "TOOL");
        addSkill("Playwright", "\\bplaywright\\b", "TOOL");
        addSkill("Selenium", "\\bselenium\\b", "TOOL");
        addSkill("Appium", "\\bappium\\b", "TOOL");
        addSkill("JMeter", "\\bjmeter\\b", "TOOL");
        addSkill("Gatling", "\\bgatling\\b", "TOOL");
        addSkill("k6", "\\bk6\\b", "TOOL");
        addSkill("Pact", "\\bpact\\b", "TOOL");
        addSkill("TDD", "\\btdd\\b|test-driven", "HARD");
        addSkill("BDD", "\\bbdd\\b|behavior-driven", "HARD");

        // Big Data, Analytics & Business Intelligence
        addSkill("Airflow", "\\b(?:apache\\s+)?airflow\\b", "HARD");
        addSkill("dbt", "\\bdbt\\b", "HARD");
        addSkill("Apache Beam", "\\b(?:apache\\s+)?beam\\b", "HARD");
        addSkill("Trino", "\\btrino\\b", "HARD");
        addSkill("Presto", "\\bpresto(?:db)?\\b", "HARD");
        addSkill("Hive", "\\b(?:apache\\s+)?hive\\b", "HARD");
        addSkill("Hadoop", "\\bhadoop\\b", "HARD");
        addSkill("ETL", "\\betl\\b", "HARD");
        addSkill("ELT", "\\belt\\b", "HARD");
        addSkill("Data Lake", "(?ui)data\\s+lake|озер[а-я]+\\s+данных", "HARD");
        addSkill("Lakehouse", "\\blakehouse\\b", "HARD");
        addSkill("Delta Lake", "\\bdelta\\s+lake\\b", "HARD");
        addSkill("Apache Iceberg", "\\b(?:apache\\s+)?iceberg\\b", "HARD");
        addSkill("Apache Hudi", "\\b(?:apache\\s+)?hudi\\b", "HARD");
        addSkill("Tableau", "\\btableau\\b", "TOOL");
        addSkill("Power BI", "\\bpower\\s+bi\\b", "TOOL");
        addSkill("Superset", "\\b(?:apache\\s+)?superset\\b", "TOOL");
        addSkill("Metabase", "\\bmetabase\\b", "TOOL");
        addSkill("Analytics & Reporting", "(?ui)analytics\\b|reporting\\s+systems?|систем[а-я]+\\s+отчетност[а-я]+", "HARD");
        addSkill("Financial Reconciliation", "(?ui)financial\\s+reconciliation|reconciliation\\s+of\\s+results|финансов[а-я]+\\s+сверк[а-я]+", "HARD");
        addSkill("Billing", "(?ui)\\bbilling\\b|биллинг[а-я]*", "HARD");

        // AI / Machine Learning & Data Science
        addSkill("Machine Learning", "(?ui)\\bml\\b|machine\\s+learning|машинн[а-я]+\\s+обучен[а-я]+", "HARD");
        addSkill("Deep Learning", "(?ui)deep\\s+learning|глубок[а-я]+\\s+обучен[а-я]+", "HARD");
        addSkill("PyTorch", "\\bpytorch\\b", "HARD");
        addSkill("TensorFlow", "\\btensorflow\\b", "HARD");
        addSkill("Keras", "\\bkeras\\b", "HARD");
        addSkill("Scikit-learn", "\\bscikit-learn|\\bsklearn\\b", "HARD");
        addSkill("Pandas", "\\bpandas\\b", "HARD");
        addSkill("NumPy", "\\bnumpy\\b", "HARD");
        addSkill("LLM", "\\bllms?\\b|large\\s+language\\s+models?", "HARD");
        addSkill("LangChain", "\\blangchain\\b", "HARD");
        addSkill("LlamaIndex", "\\bllamaindex\\b", "HARD");
        addSkill("RAG", "\\brag\\b|retrieval-augmented", "HARD");
        addSkill("HuggingFace", "\\bhugging\\s*face|\\btransformers\\b", "HARD");
        addSkill("ONNX", "\\bonnx\\b", "HARD");
        addSkill("MLflow", "\\bmlflow\\b", "HARD");
        addSkill("Computer Vision", "(?ui)computer\\s+vision|cv\\b(?!\\s*boost)", "HARD");
        addSkill("NLP", "\\bnlp\\b|natural\\s+language\\s+processing", "HARD");

        // Game Development
        addSkill("Game Development", "(?ui)game\\s+development|gamedev|разработк[а-я]+\\s+игр", "HARD");
        addSkill("DirectX", "(?ui)\\bdirectx(?:\\s*\\d+)?\\b|\\bdx\\d{1,2}\\b", "HARD");
        addSkill("OpenGL", "(?ui)\\bopengl\\b", "HARD");
        addSkill("Vulkan", "(?ui)\\bvulkan\\b", "HARD");
        addSkill("WordPress", "(?ui)\\bwordpress\\b", "TOOL");
        addSkill("Unity", "\\bunity(?:3d)?\\b", "TOOL");
        addSkill("Unreal Engine", "\\bunreal(?:\\s+engine)?\\b|\\bue[45]\\b", "TOOL");
        addSkill("Godot", "\\bgodot\\b", "TOOL");

        // Tools & Build Systems
        addSkill("Git", "\\bgit\\b(?!lab|hub)", "TOOL");
        addSkill("Maven", "\\bmaven\\b", "TOOL");
        addSkill("Gradle", "\\bgradle\\b", "TOOL");
        addSkill("npm", "\\bnpm\\b", "TOOL");
        addSkill("yarn", "\\byarn\\b", "TOOL");
        addSkill("pnpm", "\\bpnpm\\b", "TOOL");
        addSkill("Webpack", "\\bwebpack\\b", "TOOL");
        addSkill("Vite", "\\bvite(?:js)?\\b", "TOOL");
        addSkill("Jira", "\\bjira\\b", "TOOL");
        addSkill("Confluence", "\\bconfluence\\b", "TOOL");
        addSkill("Notion", "\\bnotion\\b", "TOOL");
        addSkill("Agile/Scrum", "(?ui)(?:agile|scrum|канбан|kanban)", "SOFT");
        addSkill("CI/CD", "\\bci/cd\\b", "TOOL");

        // Leadership & Soft Skills
        addSkill("Technical Leadership", "(?ui)technical\\s+leadership|техническ[а-я]+\\s+лидерств[а-я]+", "SOFT");
        addSkill("Team Lead", "(?ui)team\\s+lead|тимлид[а-я]*", "SOFT");
        addSkill("Mentoring", "(?ui)(?:менторинг|наставничество|mentoring)", "SOFT");
        addSkill("Code Review", "(?ui)(?:code\\s+review|код-ревью|код\\s+ревью)", "SOFT");
        addSkill("Architecture Governance", "(?ui)architectural\\s+decisions?|architectural\\s+planning|архитектурн[а-я]+\\s+руководств[а-я]+", "SOFT");
        addSkill("Hiring & Interviewing", "(?ui)hiring\\s+specialists|interviewing|найм[а-я]*\\s+специалистов", "SOFT");
        addSkill("Stakeholder Management", "(?ui)stakeholder\\s+management|взаимодействи[а-я]+\\s+со\\s+стейкхолдер[а-я]+", "SOFT");
        addSkill("English", "(?ui)\\benglish\\b|английск[а-я]+", "SOFT");
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
        Set<String> allSkills = extractSkills(plainText);
        Set<String> hardSkills = new LinkedHashSet<>();
        Set<String> tooling = new LinkedHashSet<>();
        Set<String> softSkills = new LinkedHashSet<>();

        for (SkillDefinition skill : SKILL_TAXONOMY) {
            if (allSkills.contains(skill.canonicalName())) {
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

    /**
     * Fast extraction of all canonical skills from arbitrary text snippet (job experience, resume section, vacancy).
     */
    public static Set<String> extractSkills(String plainText) {
        Set<String> skills = new LinkedHashSet<>();
        if (plainText == null || plainText.isBlank()) {
            return skills;
        }
        for (SkillDefinition def : SKILL_TAXONOMY) {
            if (def.pattern().matcher(plainText).find()) {
                skills.add(def.canonicalName());
            }
        }
        return skills;
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

        // Text before the first matched section header is company intro / about project
        if (!matches.isEmpty() && matches.get(0).start() > 0) {
            String intro = plainText.substring(0, matches.get(0).start()).trim();
            if (!intro.isBlank()) {
                sections.put("ABOUT_COMPANY", intro);
            }
        }

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

        if (lowerTitle.contains("principal") || lowerTitle.contains("staff")) {
            return "PRINCIPAL";
        }
        if (lowerTitle.contains("architect") || lowerTitle.contains("архитектор")) {
            return lowerTitle.contains("lead") ? "LEAD_ARCHITECT" : "ARCHITECT";
        }
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
        if (lowerText.contains("architect") || lowerText.contains("архитектор")) {
            return "ARCHITECT";
        }
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
