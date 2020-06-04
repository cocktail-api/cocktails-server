package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.model.db.DbIngredientType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeDaoIT extends DaoTestBase {

    @Autowired
    private IngredientTypeDao ingredientTypeDao;

    private static final UUID UUID_ONE = UUID.randomUUID();
    private static final UUID UUID_TWO = UUID.randomUUID();

    @Override
    protected void customInit() {
        jdbi.useHandle(h -> h.createUpdate("INSERT INTO ingredient_type (uuid, name) VALUES (:uuid, :names), (:uuid2, :names2)")
                .bind("uuid", UUID_ONE)
                .bind("names", Map.of("de", "hallo", "en", "hello"))
                .bind("uuid2", UUID_TWO)
                .bind("names2", Map.of("de", "auf wiedersehen"))
                .execute());
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
    public void testGetByIdNotFound() {
        DbIngredientType dbIngredientType = ingredientTypeDao.getById(UUID.randomUUID());

        assertNull(dbIngredientType, "Get by ID should return null for unknown ID");
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

    @Test
    public void testExists() {
        assertTrue(ingredientTypeDao.exists(UUID_ONE), "Inserted ingredient should exist");
        assertFalse(ingredientTypeDao.exists(UUID.randomUUID()), "Random UUID should not exist");
    }
}
