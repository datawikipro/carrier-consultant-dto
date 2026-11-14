package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class FrontendTrack implements CareerTrack {

    public static final String TRACK_ID = "frontend";
    public static final String DISPLAY_NAME = "Frontend Web";
    public static final String QDRANT_COLLECTION = "vacancies_frontend";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("JavaScript", "TypeScript", "React", "Next.js", "Vue.js", "HTML5", "CSS3", "Redux Toolkit", "Zustand", "TailwindCSS"),
            List.of("Git", "npm/pnpm", "Vite", "Webpack", "Chrome DevTools", "Figma", "Jest", "Playwright", "Cypress"),
            List.of("UI/UX empathy", "Design-system thinking", "Cross-functional collaboration", "Performance optimization mindset"),
            List.of("No modern SPA framework experience", "HTML/CSS only without JavaScript", "No TypeScript experience")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "JUNIOR", new GradeRubric(
                    "JUNIOR",
                    6,
                    List.of("HTML/CSS semantics", "JavaScript ES6+", "React/Vue basics", "Git", "Responsive design"),
                    List.of("Builds responsive web pages", "Integrates with REST APIs", "Understands DOM and event handling")
            ),
            "MIDDLE", new GradeRubric(
                    "MIDDLE",
                    24,
                    List.of("TypeScript strict typing", "State management (Zustand/Redux)", "SSR/SSG (Next.js)", "Testing (Jest/RTL)"),
                    List.of("Implements complex client state", "Optimizes Core Web Vitals", "Develops reusable design-system components")
            ),
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("Micro-frontends", "Web Performance profiling", "WebSockets / Realtime UI", "Architecture of large web apps"),
                    List.of("Architects frontend platforms", "Enforces code standards and automated pipelines", "Resolves complex rendering bottlenecks")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("Frontend tech strategy", "Design system governance", "Cross-team DX improvement", "Hiring & team leadership"),
                    List.of("Sets frontend technical vision", "Aligns design and engineering teams", "Scales frontend organization")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты опытный Frontend Tech Lead интервьюер. Проводи техническое собеседование по TypeScript, React/Next.js, браузерному рендерингу, оптимизации производительности (Core Web Vitals) и архитектуре клиентских приложений. Задавай практические вопросы по жизненному циклу компонентов и управлению состоянием.",
            "Ты карьерный AI-коуч. Составь убедительное сопроводительное письмо для Frontend-разработчика, акцентируя внимание на качестве UI/UX, высокой скорости работы интерфейсов и глубоком знании TypeScript и современных экосистем."
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
        return lower.contains("frontend")
                || lower.contains("фронтенд")
                || lower.contains("react")
                || lower.contains("vue")
                || lower.contains("angular")
                || lower.contains("javascript")
                || lower.contains("typescript developer")
                || lower.contains("веб-разработчик")
                || lower.contains("web developer");
    }
}
