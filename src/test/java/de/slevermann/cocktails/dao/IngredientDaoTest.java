package de.slevermann.cocktails.dao;


import de.slevermann.cocktails.model.LocalizedIngredient;
import de.slevermann.cocktails.model.db.DbCreateIngredient;
import de.slevermann.cocktails.model.db.DbIngredient;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.UnableToExecuteStatementException;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(false)
public class IngredientDaoTest extends DaoTestBase {

    @Autowired
    private IngredientDao ingredientDao;

    private static final UUID TYPE_UUID_ONE = UUID.randomUUID();

    private static final UUID TYPE_UUID_TWO = UUID.randomUUID();

    private UUID ingredientUuid;

    @Override
    protected void customInit() {
        jdbi.useHandle(h -> h.createUpdate("INSERT INTO ingredient_type (uuid, name) VALUES (:uuid, :names), (:uuid2, :names2)")
                .bind("uuid", TYPE_UUID_ONE)
                .bind("names", Map.of("de", "hallo", "en", "hello"))
                .bind("uuid2", TYPE_UUID_TWO)
                .bind("names2", Map.of("de", "auf wiedersehen"))
                .execute());
    }

    @Test
    @Order(1)
    public void testCreate() {
        DbCreateIngredient dbCreateIngredient = DbCreateIngredient.builder()
                .descriptions(Map.of("de", "eine zutat", "en", "an ingredient"))
                .names(Map.of("de", "zutat", "en", "ingredient"))
                .typeId(TYPE_UUID_ONE).build();

        DbIngredient ingredient = ingredientDao.create(dbCreateIngredient);

        assertNotNull(ingredient, "Database return value should not be null");
        assertEquals(TYPE_UUID_ONE, ingredient.getType().getUuid(), "Type UUID should match insert value");
        assertNull(ingredient.getUserInfo(), "Ingredient should not have an owner by default");
        assertTrue(ingredient.isPublic(), "Ingredient should be public by default");
        assertEquals(2, ingredient.getDescriptions().size(), "Descriptions should be properly created");
        assertEquals(2, ingredient.getNames().size(), "Names should be properly created");
        ingredientUuid = ingredient.getUuid();
    }

    @Test
    @Order(2)
    public void testGetById() {
        DbIngredient ingredient = ingredientDao.getById(ingredientUuid);

        assertNotNull(ingredient, "Database return value should not be null");
        assertEquals(TYPE_UUID_ONE, ingredient.getType().getUuid(), "Type UUID should match insert value");
        assertNull(ingredient.getUserInfo(), "Ingredient should not have an owner by default");
        assertTrue(ingredient.isPublic(), "Ingredient should be public by default");
        assertEquals(2, ingredient.getDescriptions().size(), "Descriptions should be properly created");
        assertEquals(2, ingredient.getNames().size(), "Names should be properly created");
    }


    @Test
    @Order(3)
    @Rollback
    public void testDelete() {
        ingredientDao.delete(ingredientUuid);
        DbIngredient dbIngredient = ingredientDao.getById(ingredientUuid);

        assertNull(dbIngredient, "Ingredient should not be returned after delete");
    }

    @Test
    @Order(4)
    public void testGetAll() {
        DbCreateIngredient secondIngredient = DbCreateIngredient.builder()
                .descriptions(Map.of("de", "eine zutat"))
                .names(Map.of("de", "zutat"))
                .typeId(TYPE_UUID_TWO).build();

        ingredientDao.create(secondIngredient);

        List<LocalizedIngredient> localizedIngredients = ingredientDao.getAll("en");

        assertEquals(2, localizedIngredients.size(), "Both ingredients should be present in the database");

        for (LocalizedIngredient localizedIngredient : localizedIngredients) {
            if (localizedIngredient.getType().getId().equals(TYPE_UUID_TWO)) {
                assertEquals("de", localizedIngredient.getLanguage(),
                        "German should be chosen if requested english is not available");
            } else {
                assertEquals("en", localizedIngredient.getLanguage(),
                        "English should be chosen if it is available");
            }
        }
    }

    @Test
    @Order(5)
    @Rollback
    public void testUpdate() {
        DbCreateIngredient dbCreateIngredient = DbCreateIngredient.builder()
                .descriptions(Map.of("de", "update", "en", "an updated ingredient"))
                .names(Map.of("de", "ein update", "en", "updated ingredient"))
                .typeId(TYPE_UUID_TWO).build();

        DbIngredient updatedIngredient = ingredientDao.update(ingredientUuid, dbCreateIngredient);
        assertNotNull(updatedIngredient, "Updated ingredient should be returned");
        assertEquals("update", updatedIngredient.getDescriptions().get("de"),
                "German description should be updated");
        assertEquals("an updated ingredient", updatedIngredient.getDescriptions().get("en"),
                "English description should be updated");
        assertEquals("ein update", updatedIngredient.getNames().get("de"),
                "German name should be updated");
        assertEquals("updated ingredient", updatedIngredient.getNames().get("en"),
                "English name should be updated");
        assertEquals(TYPE_UUID_TWO, updatedIngredient.getType().getUuid());

        DbIngredient updatedFromDb = ingredientDao.getById(ingredientUuid);
        assertEquals("update", updatedFromDb.getDescriptions().get("de"),
                "German description should be updated");
        assertEquals("an updated ingredient", updatedFromDb.getDescriptions().get("en"),
                "English description should be updated");
        assertEquals("ein update", updatedFromDb.getNames().get("de"),
                "German name should be updated");
        assertEquals("updated ingredient", updatedFromDb.getNames().get("en"),
                "English name should be updated");
        assertEquals(TYPE_UUID_TWO, updatedFromDb.getType().getUuid());
    }

    // TODO: Tests for search, tests for invalid updates, tests for owner and public fields

    @Test
    public void testCreateMismatch() {
        DbCreateIngredient secondIngredient = DbCreateIngredient.builder()
                .descriptions(Map.of("de", "eine zutat"))
                .names(Map.of("de", "zutat", "en", "ingredient"))
                .typeId(TYPE_UUID_TWO).build();

        assertThrows(UnableToExecuteStatementException.class, () -> ingredientDao.create(secondIngredient),
                "A mismatch in languages between descriptions and names should throw an error");
    }

    @Test
    public void testCreateEmpty() {
        DbCreateIngredient secondIngredient = DbCreateIngredient.builder()
                .descriptions(Map.of())
                .names(Map.of())
                .typeId(TYPE_UUID_TWO).build();

        assertThrows(UnableToExecuteStatementException.class, () -> ingredientDao.create(secondIngredient),
                "Empty names and descriptions should throw an error");
    }
}