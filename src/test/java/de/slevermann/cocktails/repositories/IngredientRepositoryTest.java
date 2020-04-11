package de.slevermann.cocktails.repositories;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientName;
import de.slevermann.cocktails.models.IngredientType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class IngredientRepositoryTest {

    @Autowired
    private IngredientRepository ingredientRepository;

    @BeforeEach
    public void beforeEach() {
        this.ingredientRepository.deleteAll();
    }

    @Test
    public void testFetchByNameAndLocale() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        IngredientName nameGerman = new IngredientName();
        nameGerman.setIngredient(ingredient);
        nameGerman.setLocale("de-DE");
        nameGerman.setName("Wodka");

        List<IngredientName> names = new ArrayList<>();
        names.add(nameGerman);
        ingredient.setNames(names);

        ingredientRepository.save(ingredient);

        Ingredient ingredient1 = ingredientRepository.findByNameAndLocale("Wodka", "de-DE");

        assertNotNull(ingredient1, "Repository should find a single ingredient matching the constraint");
    }

    @Test
    public void testAddCollidingLocaleAndId() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        IngredientName nameGerman = new IngredientName();
        nameGerman.setIngredient(ingredient);
        nameGerman.setLocale("de-DE");
        nameGerman.setName("Wodka");

        List<IngredientName> names = new ArrayList<>();
        names.add(nameGerman);
        ingredient.setNames(names);

        Ingredient saved = ingredientRepository.save(ingredient);

        IngredientName newName = new IngredientName();
        newName.setIngredient(saved);
        newName.setName("Vodka");
        newName.setLocale("de-DE");
        saved.getNames().add(newName);

        assertThrows(DataIntegrityViolationException.class, () -> ingredientRepository.save(saved), "Saving another ingredient name with the same Locale and ingredient ID should fail");
    }

    @Test
    public void testAddCollidingLocaleAndName() {
        Ingredient ingredient = new Ingredient();
        ingredient.setType(IngredientType.HARD_LIQUOR);

        IngredientName nameGerman = new IngredientName();
        nameGerman.setIngredient(ingredient);
        nameGerman.setLocale("de-DE");
        nameGerman.setName("Wodka");

        List<IngredientName> names = new ArrayList<>();
        names.add(nameGerman);
        ingredient.setNames(names);

        ingredientRepository.save(ingredient);

        Ingredient other = new Ingredient();
        other.setType(IngredientType.HARD_LIQUOR);

        IngredientName badName = new IngredientName();
        badName.setIngredient(other);
        badName.setLocale("de-DE");
        badName.setName("Wodka");

        List<IngredientName> badNames = new ArrayList<>();
        badNames.add(badName);
        other.setNames(badNames);

        assertThrows(DataIntegrityViolationException.class, () -> ingredientRepository.save(other),
                "A new ingredient name that matches an old name by name and locale should throw an error");
    }
}
