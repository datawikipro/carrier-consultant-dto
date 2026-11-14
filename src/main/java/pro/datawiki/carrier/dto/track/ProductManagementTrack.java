package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ProductManagementTrack implements CareerTrack {

    public static final String TRACK_ID = "product-management";
    public static final String DISPLAY_NAME = "Product Management";
    public static final String QDRANT_COLLECTION = "vacancies_product_management";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("Product Strategy", "Product Analytics", "Unit Economics", "Customer Discovery", "User Stories", "Roadmapping", "A/B Testing", "Feature Prioritization", "CJM"),
            List.of("Jira", "Confluence", "Notion", "Amplitude", "Mixpanel", "Figma", "SQL", "Miro"),
            List.of("Stakeholder Management", "Leadership without authority", "Strategic communication", "Empathy", "Negotiation"),
            List.of("No product ownership experience", "Lack of analytical/metrics comprehension", "Pure project management without product vision")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "JUNIOR", new GradeRubric(
                    "JUNIOR",
                    6,
                    List.of("Backlog grooming", "User story writing", "Basic product metrics", "Competitor research"),
                    List.of("Collects user feedback", "Tracks sprint execution", "Conducts basic user interviews")
            ),
            "MIDDLE", new GradeRubric(
                    "MIDDLE",
                    24,
                    List.of("Unit economics modeling", "A/B test design", "Product discovery frameworks", "Roadmapping"),
                    List.of("Owns feature lifecycle from idea to release", "Improves key product funnel metrics", "Prioritizes based on ROI")
            ),
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("Product-Market Fit expansion", "Monetization strategy", "Multi-stream alignment", "Strategic vision"),
                    List.of("Owns full product domain", "Drives significant business revenue growth", "Balances technical debt and business value")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("CPO / Head of Product leadership", "Company-wide product strategy", "Product culture coaching", "Executive alignment"),
                    List.of("Shapes company product direction", "Mentors Product Managers", "Drives company-level North Star metrics")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты опытный CPO и Head of Product интервьюер. Проводи глубокое собеседование по продуктовому мышлению, юнит-экономике, приоритизации гипотез, работе с метриками (CAC, LTV, Retention) и стейкхолдер-менеджменту. Оценивай способность кандидата принимать взвешенные решения на основе данных.",
            "Ты карьерный AI-коуч. Составь убедительное сопроводительное письмо для Product Manager, подчеркивая измеримые результаты роста продуктовых метрик, успешный запуск продуктов и эффективную работу с кросс-функциональной командой."
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
        return lower.contains("product manager")
                || lower.contains("продакт")
                || lower.contains("product owner")
                || lower.contains("владелец продукта")
                || lower.contains("cpo")
                || lower.contains("head of product")
                || lower.contains("менеджер продукта");
    }
}
