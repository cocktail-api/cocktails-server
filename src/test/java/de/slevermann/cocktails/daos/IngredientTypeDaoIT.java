package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.JdbiTest;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.LocalizedIngredientType;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbiTest
public class IngredientTypeDaoIT extends DaoTestBase {

    private final IngredientTypeDao ingredientTypeDao;

    @Autowired
    public IngredientTypeDaoIT(Jdbi jdbi, IngredientTypeDao ingredientTypeDao, Jackson2ObjectMapperBuilder builder) {
        super(jdbi, builder);
        this.ingredientTypeDao = ingredientTypeDao;
    }

    @Test
    public void testGetById() throws Exception {
        IngredientType ingredientType = loadIngredientType("multiple_names.json");

        Long id = ingredientType.getId();

        insertIngredientType(ingredientType);

        IngredientType fromDb = ingredientTypeDao.getById(id);

        assertEquals(ingredientType, fromDb, "The object from the database should equal the object we inserted");

        HashMap<String, String> expected = new HashMap<>(ingredientType.getNames());
        HashMap<String, String> actual = new HashMap<>(fromDb.getNames());

        // This is necessary because swagger-codegen has a bug in the map code generation
        assertEquals(expected, actual, "The object from the database should equal the object we inserted");
    }

    @Test
    public void testGetAll() throws Exception {
        IngredientType first = loadIngredientType("multiple_names.json");
        IngredientType second = loadIngredientType("only_german_name.json");

        insertIngredientType(first);
        insertIngredientType(second);

        List<LocalizedIngredientType> types = ingredientTypeDao.getAll("en");

        assertEquals(2, types.size(), "There should be two types in the database");
    }

    @Test
    public void testLocalizationRulesMultiple() throws Exception {
        IngredientType multipleNames = loadIngredientType("multiple_names.json");
        insertIngredientType(multipleNames);

        for (String locale : new String[]{"de", "en"}) {
            List<LocalizedIngredientType> types = ingredientTypeDao.getAll(locale);
            assertEquals(locale, types.get(0).getLanguage(),
                    "Language should match requested language if it exists");
        }

        List<LocalizedIngredientType> types = ingredientTypeDao.getAll("unavailable");
        assertEquals("en", types.get(0).getLanguage(),
                "For an unavailable locale, english should be chosen as a fallback");
    }

    @Test
    public void testLocalizationRulesSingle() throws Exception {
        IngredientType singleName = loadIngredientType("only_german_name.json");
        insertIngredientType(singleName);

        List<LocalizedIngredientType> types = ingredientTypeDao.getAll("unavailable");
        assertEquals("de", types.get(0).getLanguage(),
                "For an unavailable locale, german should be chosen if english also isn't available");
    }
}
