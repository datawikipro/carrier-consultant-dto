package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DevOpsTrack implements CareerTrack {

    public static final String TRACK_ID = "devops";
    public static final String DISPLAY_NAME = "DevOps & SRE";
    public static final String QDRANT_COLLECTION = "vacancies_devops";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("Linux", "Kubernetes", "Docker", "CI/CD", "Terraform", "Ansible", "Prometheus", "Grafana", "Helm", "PostgreSQL Administration", "Nginx"),
            List.of("Git", "GitLab CI", "GitHub Actions", "ArgoCD", "Bash", "Python", "Vault", "ELK/Loki"),
            List.of("Incident response", "Reliability mindset (SLO/SLA)", "Cross-team enablement", "Automation mindset"),
            List.of("No Linux experience", "Manual infrastructure configuration only", "No container experience")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "JUNIOR", new GradeRubric(
                    "JUNIOR",
                    6,
                    List.of("Linux sysadmin basics", "Docker containerization", "Basic Bash scripting", "CI/CD runner setup"),
                    List.of("Maintains build pipelines", "Deploys containerized applications", "Understands networking and DNS")
            ),
            "MIDDLE", new GradeRubric(
                    "MIDDLE",
                    24,
                    List.of("Kubernetes administration", "Infrastructure as Code (Terraform)", "Monitoring & Alerting", "GitOps (ArgoCD)"),
                    List.of("Manages multi-environment clusters", "Automates release pipelines", "Implements robust monitoring and logging")
            ),
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("High-availability cloud architecture", "Disaster Recovery", "Zero-downtime migrations", "Chaos Engineering"),
                    List.of("Architects hybrid and multi-cloud solutions", "Sets reliability standards (SLO/SLI)", "Drives platform engineering culture")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("Infrastructure strategy", "DevSecOps governance", "Cost optimization (FinOps)", "Team leadership"),
                    List.of("Defines enterprise infrastructure roadmaps", "Leads incident post-mortems", "Scales platform teams")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты опытный DevOps/SRE Lead интервьюер. Проводи глубокое техническое собеседование по Kubernetes, сетям Linux, Terraform, GitOps и практикам обеспечения отказоустойчивости (SLO/SLI). Оценивай способность кандидата предотвращать инциденты и масштабировать инфраструктуру под высокими нагрузками.",
            "Ты карьерный AI-коуч. Составь убедительное сопроводительное письмо для DevOps / SRE инженера, подчеркивая опыт автоматизации с Kubernetes, Terraform, ArgoCD и обеспечения аптайма 99.99%."
    );

    @Override
    public String getTrackId() {
        return TRACK_ID;
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getQdrantCollectionName() {
        return QDRANT_COLLECTION;
    }

    @Override
    public int getVectorDimension() {
        return VECTOR_DIMENSION;
    }

    @Override
    public SkillClusters getSkillClusters() {
        return SKILL_CLUSTERS;
    }

    @Override
    public Map<String, GradeRubric> getGradeRubrics() {
        return RUBRICS;
    }

    @Override
    public TrackPromptTemplates getPromptTemplates() {
        return PROMPT_TEMPLATES;
    }

    @Override
    public boolean matches(String professionTitle) {
        if (professionTitle == null) {
            return false;
        }
        String lower = professionTitle.toLowerCase(Locale.ROOT);
        return lower.contains("devops")
                || lower.contains("девопс")
                || lower.contains("sre")
                || lower.contains("site reliability")
                || lower.contains("системный администратор")
                || lower.contains("system administrator")
                || lower.contains("инженер инфраструктуры")
                || lower.contains("cloud engineer")
                || lower.contains("platform engineer");
    }
}
