package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.IngredientType;
import de.slevermann.cocktails.model.db.DbIngredientType;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTypeMapperTest {

    private final IngredientTypeMapper ingredientTypeMapper = new IngredientTypeMapper(new TranslatedStringMapper());

    @Test
    public void testMapping() {
        UUID uuid = UUID.randomUUID();
        DbIngredientType dbIngredientType = DbIngredientType.builder()
                .names(Map.of("de", "hallo", "en", "hello"))
                .uuid(uuid).build();

        IngredientType ingredientType = ingredientTypeMapper.dbIngredientTypetoIngredientType(dbIngredientType);

        assertEquals(2, ingredientType.getNames().size(), "All name entries should be present");
        assertEquals(uuid, ingredientType.getId(), "UUID should match");
    }
}
