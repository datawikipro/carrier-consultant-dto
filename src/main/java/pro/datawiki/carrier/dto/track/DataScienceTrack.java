package pro.datawiki.carrier.dto.track;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DataScienceTrack implements CareerTrack {

    public static final String TRACK_ID = "data-science";
    public static final String DISPLAY_NAME = "Data Science & AI";
    public static final String QDRANT_COLLECTION = "vacancies_data_science";
    public static final int VECTOR_DIMENSION = 1536;

    private static final SkillClusters SKILL_CLUSTERS = new SkillClusters(
            List.of("Python", "PyTorch", "TensorFlow", "Pandas", "NumPy", "Scikit-learn", "SQL", "Machine Learning", "Deep Learning", "LLM", "NLP"),
            List.of("Jupyter", "Git", "MLflow", "Docker", "CUDA", "Weights & Biases", "Airflow", "DVC"),
            List.of("Data storytelling", "Experimentation mindset", "Analytical thinking", "Business problem framing"),
            List.of("Lack of mathematical background", "No Python or SQL experience", "Lack of practical ML modeling")
    );

    private static final Map<String, GradeRubric> RUBRICS = Map.of(
            "JUNIOR", new GradeRubric(
                    "JUNIOR",
                    6,
                    List.of("Python data stack", "EDA (Exploratory Data Analysis)", "Classical ML models", "SQL queries"),
                    List.of("Performs feature engineering", "Evaluates models with standard metrics", "Prepares data pipelines")
            ),
            "MIDDLE", new GradeRubric(
                    "MIDDLE",
                    24,
                    List.of("Deep Learning", "NLP / Computer Vision", "MLOps basics", "Model deployment (FastAPI/Triton)"),
                    List.of("Fine-tunes transformer models", "Builds automated training pipelines", "Validates models against business KPIs")
            ),
            "SENIOR", new GradeRubric(
                    "SENIOR",
                    48,
                    List.of("Distributed training", "LLM architectures / RAG", "System Design for ML", "Quantization & Optimization"),
                    List.of("Architects enterprise ML platforms", "Formulates R&D hypotheses", "Solves complex business problems end-to-end")
            ),
            "LEAD", new GradeRubric(
                    "LEAD",
                    72,
                    List.of("AI Strategy", "Research leadership", "Portfolio of ML initiatives", "Cross-departmental sponsorship"),
                    List.of("Guides AI roadmap", "Builds high-performing Data Science org", "Ensures ethical and compliant AI")
            )
    );

    private static final TrackPromptTemplates PROMPT_TEMPLATES = new TrackPromptTemplates(
            "Ты ведущий Data Science и AI интервьюер. Проводи глубокое техническое интервью по машинному обучению, статистике, архитектурам нейросетей, метрикам качества и MLOps. Оценивай глубину математической интуиции и практический опыт решения нестандартных ML-задач.",
            "Ты карьерный AI-коуч. Составь сильное сопроводительное письмо для позиции Data Scientist / ML Engineer, фокусируясь на бизнес-эффекте внедренных ML-моделей, опыте работы с LLM/RAG и надежном пайплайне экспериментов."
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
        return lower.contains("data scientist")
                || lower.contains("дата сайн")
                || lower.contains("machine learning")
                || lower.contains("ml engineer")
                || lower.contains("deep learning")
                || lower.contains("nlp")
                || lower.contains("computer vision")
                || lower.contains("аналитик данных")
                || lower.contains("data analyst")
                || lower.contains("ai engineer")
                || lower.contains("llm");
    }
}
