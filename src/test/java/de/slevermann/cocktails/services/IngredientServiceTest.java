package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredientServiceTest {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private Jdbi jdbi;

    @BeforeEach
    public void beforeEach() {
        try (Handle h = jdbi.open()) {
            h.createUpdate("TRUNCATE ingredient CASCADE");
        }
    }

    @AfterEach
    public void afterEach() {
        beforeEach();
    }

    @Test
    public void testCreate() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        Long id = ingredientService.createIngredient(ingredient);

        assertNotNull(id, "Valid ingredient should be successfully created");
    }


    @Test
    public void testCreateInvalid() {
        Ingredient ingredient = new Ingredient();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class, () -> ingredientService.createIngredient(ingredient));

        assertEquals(1, exception.getConstraintViolations().size(), "Null type should create one violation");
    }
}
