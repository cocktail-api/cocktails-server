package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class IngredientDaoIT extends DaoTestBase {

    private final IngredientDao ingredientDao;

    @Autowired
    protected IngredientDaoIT(Jdbi jdbi, Jackson2ObjectMapperBuilder builder, IngredientDao ingredientDao) {
        super(jdbi, builder);
        this.ingredientDao = ingredientDao;
    }

    @Test
    public void testGetById() throws Exception {
        Ingredient ingredient = loadIngredient("multiple_names.json");
        IngredientType type = loadIngredientType("multiple_names.json");
        insertIngredientType(type);
        ingredient.setType(type);

        insertIngredient(ingredient);

        assertEquals(ingredient, ingredientDao.getById(ingredient.getId()), "Retrieved value should equal inserted value");
    }

    @Test
    public void testDelete() throws Exception {
        Ingredient ingredient = loadIngredient("multiple_names.json");
        IngredientType type = loadIngredientType("multiple_names.json");
        insertIngredientType(type);
        ingredient.setType(type);

        insertIngredient(ingredient);

        assertEquals(ingredient, ingredientDao.getById(ingredient.getId()), "Retrieved value should equal inserted value");
        ingredientDao.delete(ingredient.getId());
        assertNull(ingredientDao.getById(ingredient.getId()), "Value should be gone after deleting");
    }

    @Test
    public void testGetAll() throws Exception {
        Ingredient ingredient1 = loadIngredient("multiple_names.json");
        IngredientType type1 = loadIngredientType("multiple_names.json");
        insertIngredientType(type1);
        ingredient1.setType(type1);

        insertIngredient(ingredient1);

        Ingredient ingredient2 = loadIngredient("only_english.json");
        IngredientType type2 = loadIngredientType("only_german_name.json");
        insertIngredientType(type2);
        ingredient2.setType(type2);

        insertIngredient(ingredient2);

        assertEquals(2, ingredientDao.getAll("de").size(), "GetAll should return all entries");
    }


}
