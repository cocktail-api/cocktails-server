package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.ContainerTestBase;
import de.slevermann.cocktails.JdbiTest;
import de.slevermann.cocktails.model.LocalizedIngredientType;
import de.slevermann.cocktails.model.db.DbIngredientType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@JdbiTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IngredientTypeDaoTest extends ContainerTestBase {

    @Autowired
    private Jdbi jdbi;

    @Autowired
    private IngredientTypeDao ingredientTypeDao;

    private static final UUID UUID_ONE = UUID.randomUUID();
    private static final UUID UUID_TWO = UUID.randomUUID();

    @BeforeAll
    public void beforeAll() {
        jdbi.useHandle(h -> {
            h.execute("TRUNCATE TABLE ingredient CASCADE");
            h.execute("TRUNCATE TABLE ingredient_type CASCADE");
            h.execute("TRUNCATE TABLE \"user\" CASCADE");
            h.createUpdate("INSERT INTO ingredient_type (uuid, name) VALUES (:uuid, :names), (:uuid2, :names2)")
                    .bind("uuid", UUID_ONE)
                    .bind("names", Map.of("de", "hallo", "en", "hello"))
                    .bind("uuid2", UUID_TWO)
                    .bind("names2", Map.of("de", "auf wiedersehen"))
                    .execute();
        });
    }

    @AfterAll
    public void afterAll() {
        jdbi.useHandle(h -> {
            h.execute("TRUNCATE TABLE ingredient CASCADE");
            h.execute("TRUNCATE TABLE ingredient_type CASCADE");
            h.execute("TRUNCATE TABLE \"user\" CASCADE");
        });
    }


    @Test
    public void testGetById() {
        DbIngredientType dbIngredientType = ingredientTypeDao.getById(UUID_ONE);

        assertEquals(UUID_ONE, dbIngredientType.getUuid(), "UUID for returned type should match");
        assertEquals(2, dbIngredientType.getNames().size());
        assertEquals("hallo", dbIngredientType.getNames().get("de"));
        assertEquals("hello", dbIngredientType.getNames().get("en"));
    }

    @Test
    public void testGetAll() {
        List<LocalizedIngredientType> dbIngredientTypes = ingredientTypeDao.getAll("en");

        assertEquals(2, dbIngredientTypes.size(), "There should be two elements in the database");

        for (LocalizedIngredientType localizedIngredientType : dbIngredientTypes) {
            if (localizedIngredientType.getId().equals(UUID_ONE)) {
                assertEquals("en", localizedIngredientType.getLanguage(),
                        "English message should be chosen when available");
            } else {
                assertEquals("de", localizedIngredientType.getLanguage(),
                        "German message should be chosen when english is not available");
            }
        }
    }
}
