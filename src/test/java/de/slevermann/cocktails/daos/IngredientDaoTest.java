package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dbtest")
@Transactional
public class IngredientDaoTest {

    @Autowired
    private IngredientDao ingredientDao;

    @Autowired
    private Jdbi jdbi;

    @BeforeEach
    public void beforeEach() {
        jdbi.open().createUpdate("TRUNCATE ingredient CASCADE").execute();
    }

    @AfterEach
    public void afterEach() {
        beforeEach();
    }

    @Test
    public void testCreateNullType() {
        Ingredient ingredient = new Ingredient();

        assertThrows(Exception.class,
                () -> ingredientDao.insert(ingredient), "Creating an ingredient with no type should throw");
    }

    @Test
    public void testCreateValid() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        Long id = ingredientDao.insert(ingredient);
        assertNotNull(id, "Insert should return a valid ID");
    }

    @Test
    public void testCreateMultiple() {
        Ingredient one = new Ingredient();
        one.setType(IngredientType.HARD_LIQUOR);
        Ingredient two = new Ingredient();
        two.setType(IngredientType.SOFT_DRINK);

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(one);
        ingredients.add(two);

        List<Long> ids = ingredientDao.batchInsert(ingredients);

        assertEquals(2, ids.size(), "Batch insert should return two IDs");
        for (Long id : ids) {
            assertNotNull(id, "Created IDs should not be null");
        }
    }

    @Test
    public void testCreateMultipleOneInvalid() {
        Ingredient valid = new Ingredient();
        valid.setType(IngredientType.HARD_LIQUOR);
        Ingredient invalid = new Ingredient();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(valid);
        ingredients.add(invalid);

        Long countBefore = ingredientDao.count();

        assertThrows(Exception.class, () -> ingredientDao.batchInsert(ingredients),
                "Batch insert with an invalid object should throw");

        assertEquals(countBefore, ingredientDao.count(), "No objects should have been created if one is invalid");
    }

    @Test
    public void testCreateMultipleOneInvalidInvalidFirst() {
        Ingredient valid = new Ingredient();
        valid.setType(IngredientType.HARD_LIQUOR);
        Ingredient invalid = new Ingredient();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(invalid);
        ingredients.add(valid);

        Long countBefore = ingredientDao.count();

        assertThrows(Exception.class, () -> ingredientDao.batchInsert(ingredients),
                "Batch insert with an invalid object should throw");

        assertEquals(countBefore, ingredientDao.count(), "No objects should have been created if one is invalid");
    }

    @Test
    public void testCount() {
        assertEquals(0, ingredientDao.count(), "Initial count should be zero");

        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        ingredientDao.insert(ingredient);

        assertEquals(1, ingredientDao.count(), "Count should be one after single insert");

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ingredients.add(ingredient);
        }
        ingredientDao.batchInsert(ingredients);

        assertEquals(11, ingredientDao.count(), "Count should be 11 after batch insert");
    }

    @Test
    public void testFindById() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        Long id = ingredientDao.insert(ingredient);
        ingredient.setId(id);
        Ingredient found = ingredientDao.findById(id);

        assertEquals(ingredient, found, "Found ingredient should be equal to inserted ingredient");
    }

    @Test
    public void testDelete() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        Long id = ingredientDao.insert(ingredient);

        ingredientDao.deleteById(id);

        assertNull(ingredientDao.findById(id), "Object should not be found after deletion");
    }

    @Test
    public void testBatchDelete() {
        Ingredient nonDeleted = new Ingredient();
        nonDeleted.setType(IngredientType.HARD_LIQUOR);

        Long id = ingredientDao.insert(nonDeleted);

        Ingredient deleted = new Ingredient();
        deleted.setType(IngredientType.JUICE);

        List<Ingredient> ingredients = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ingredients.add(deleted);
        }

        List<Long> ids = ingredientDao.batchInsert(ingredients);

        assertEquals(10, ids.size(), "10 Elements should have been inserted");

        ingredientDao.batchDelete(ids);

        for (Long deletedId : ids) {
            assertNull(ingredientDao.findById(deletedId), "Deleted object should not be found again");
        }
    }
}
