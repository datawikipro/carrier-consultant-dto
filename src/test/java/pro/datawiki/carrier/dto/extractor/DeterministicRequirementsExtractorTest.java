package pro.datawiki.carrier.dto.extractor;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DeterministicRequirementsExtractorTest {

    @Test
    void testExtractStandardJavaVacancy() {
        String description = """
                <p>Мы — ведущая финтех-компания. Разрабатываем высоконагруженные платежные сервисы.</p>
                <p><strong>Обязанности:</strong></p>
                <ul>
                    <li>Разработка микросервисов на Java 21 и Spring Boot</li>
                    <li>Проектирование схем БД PostgreSQL и оптимизация запросов</li>
                    <li>Интеграция с Kafka для асинхронного обмена событиями</li>
                </ul>
                <p><strong>Требования:</strong></p>
                <ul>
                    <li>Опыт коммерческой разработки на Java от 3 до 6 лет</li>
                    <li>Глубокое понимание Spring Boot, Spring Data JPA, Hibernate</li>
                    <li>Опыт работы с PostgreSQL, Redis и Docker</li>
                    <li>Понимание принципов микросервисной архитектуры и REST API</li>
                </ul>
                <p><strong>Будет плюсом:</strong></p>
                <ul>
                    <li>Опыт работы с Kubernetes и CI/CD (GitLab CI)</li>
                    <li>Знание Kotlin и gRPC</li>
                </ul>
                <p><strong>Мы предлагаем:</strong></p>
                <ul>
                    <li>Полностью удаленная работа (remote)</li>
                    <li>ДМС со стоматологией, компенсация спорта</li>
                    <li>Гибкое начало рабочего дня</li>
                </ul>
                """;

        String title = "Senior Java Developer";

        StructuredVacancyRequirements result = DeterministicRequirementsExtractor.extract(description, title);

        assertNotNull(result);
        assertEquals("backend-java", result.getCareerTrackId());
        assertEquals("SENIOR", result.getGrade());
        assertEquals("remote", result.getWorkFormat());
        assertTrue(result.getRemoteAvailable());
        assertEquals(3.0, result.getExperienceMinYears());
        assertEquals(6.0, result.getExperienceMaxYears());

        // Verify sections
        assertFalse(result.getRequirements().isEmpty(), "Requirements should not be empty");
        assertFalse(result.getResponsibilities().isEmpty(), "Responsibilities should not be empty");
        assertFalse(result.getConditions().isEmpty(), "Conditions should not be empty");
        assertFalse(result.getNiceToHave().isEmpty(), "Nice to have should not be empty");

        // Verify skills extracted without LLM
        assertTrue(result.getAllExtractedSkills().contains("Java"), "Should contain Java");
        assertTrue(result.getAllExtractedSkills().contains("Spring Boot"), "Should contain Spring Boot");
        assertTrue(result.getAllExtractedSkills().contains("PostgreSQL"), "Should contain PostgreSQL");
        assertTrue(result.getAllExtractedSkills().contains("Kafka"), "Should contain Kafka");
        assertTrue(result.getAllExtractedSkills().contains("Docker"), "Should contain Docker");
        assertTrue(result.getAllExtractedSkills().contains("Kubernetes"), "Should contain Kubernetes");
        assertTrue(result.getAllExtractedSkills().contains("Redis"), "Should contain Redis");
        assertTrue(result.getAllExtractedSkills().contains("Kotlin"), "Should contain Kotlin");
        assertTrue(result.getAllExtractedSkills().contains("gRPC"), "Should contain gRPC");

        // Verify categorized tiers
        assertTrue(result.getHardSkills().contains("Java"));
        assertTrue(result.getHardSkills().contains("Spring Boot"));
        assertTrue(result.getTooling().contains("Docker"));
        assertTrue(result.getTooling().contains("Kubernetes"));
    }

    @Test
    void testExtractCandidateResume() {
        String rawResume = """
                Александр Черноусов
                Желаемая должность: Lead Data Engineer / Senior Java Developer
                Зарплата: 400 000 руб.
                Контакты:
                Email: alex@datawiki.pro
                Telegram: @alex_chernousov
                Телефон: +7 (911) 123-45-67
                GitHub: https://github.com/alex-datawiki
                
                Опыт работы: 8 лет 6 месяцев
                
                О себе:
                Эксперт в проектировании высоконагруженных распределенных платформ, микросервисов на Java 21 и Spring Boot.
                Стек: Java, Spring Boot, Spring Security, Kafka, PostgreSQL, Docker, Kubernetes, ClickHouse, Apache Spark, Airflow.
                """;

        StructuredCandidateProfile profile = StructuredResumeExtractor.extract(rawResume, "Lead Data Engineer");

        assertNotNull(profile);
        assertEquals("LEAD", profile.getGrade());
        assertEquals(400000, profile.getDesiredSalaryMin());
        assertEquals("RUB", profile.getSalaryCurrency());
        assertEquals(8.5, profile.getExperienceYearsTotal());

        // Contacts
        assertEquals("alex@datawiki.pro", profile.getContacts().get("EMAIL"));
        assertEquals("@alex_chernousov", profile.getContacts().get("TELEGRAM"));
        assertEquals("+7 (911) 123-45-67", profile.getContacts().get("PHONE"));
        assertEquals("https://github.com/alex-datawiki", profile.getContacts().get("GITHUB"));

        // Skills
        assertTrue(profile.getAllSkills().contains("Java"));
        assertTrue(profile.getAllSkills().contains("Spring Boot"));
        assertTrue(profile.getAllSkills().contains("Kafka"));
        assertTrue(profile.getAllSkills().contains("PostgreSQL"));
        assertTrue(profile.getAllSkills().contains("Docker"));
        assertTrue(profile.getAllSkills().contains("Kubernetes"));
        assertTrue(profile.getAllSkills().contains("Spark"));
        assertTrue(profile.getAllSkills().contains("Airflow"));
    }
}
