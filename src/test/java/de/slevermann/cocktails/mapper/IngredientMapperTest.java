package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.Ingredient;
import de.slevermann.cocktails.dto.TranslatedString;
import de.slevermann.cocktails.model.db.DbCreateIngredient;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.db.DbUpdateIngredient;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientMapperTest {

    private final TranslatedStringMapper translatedStringMapper = new TranslatedStringMapper();

    private final IngredientMapper ingredientMapper = new IngredientMapper(
            new UserInfoMapper(),
            new IngredientTypeMapper(translatedStringMapper),
            translatedStringMapper);

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testMapDbIngredient(boolean publicStatus) {
        UUID uuid = UUID.randomUUID();
        DbIngredient dbIngredient = DbIngredient.builder()
                .descriptions(Map.of("de", "Gruß", "en", "Greeting"))
                .names(Map.of("de", "hallo", "en", "hello"))
                .published(publicStatus)
                .type(DbIngredientType.builder()
                        .uuid(UUID.randomUUID())
                        .names(Map.of("de", "hallo", "en", "hello")).build())
                .uuid(uuid)
                .userInfo(DbUserInfo.builder()
                        .nick("john.doe")
                        .uuid(UUID.randomUUID())
                        .providerId("providerId").build()).build();

        Ingredient ingredient = ingredientMapper.dbIngredientToIngredient(dbIngredient);

        assertEquals(2, ingredient.getDescriptions().size(), "Descriptions should be present");
        assertEquals(2, ingredient.getNames().size(), "Names should be present");
        assertEquals(dbIngredient.isPublished(), ingredient.isPublished(), "Public status should be mapped");
        assertEquals(uuid, ingredient.getId(), "ID should be mapped");
        assertNotNull(ingredient.getOwner(), "User info should be mapped");
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testMapDbIngredientEmptyUserInfo(boolean publicStatus) {
        UUID uuid = UUID.randomUUID();
        DbIngredient dbIngredient = DbIngredient.builder()
                .descriptions(Map.of("de", "Gruß", "en", "Greeting"))
                .names(Map.of("de", "hallo", "en", "hello"))
                .published(publicStatus)
                .type(DbIngredientType.builder()
                        .uuid(UUID.randomUUID())
                        .names(Map.of("de", "hallo", "en", "hello")).build())
                .uuid(uuid).build();

        Ingredient ingredient = ingredientMapper.dbIngredientToIngredient(dbIngredient);

        assertEquals(2, ingredient.getDescriptions().size(), "Descriptions should be present");
        assertEquals(2, ingredient.getNames().size(), "Names should be present");
        assertEquals(dbIngredient.isPublished(), ingredient.isPublished(), "Public status should be mapped");
        assertEquals(uuid, ingredient.getId(), "ID should be mapped");
        assertNull(ingredient.getOwner(), "User info should be absent");
    }

    @ParameterizedTest
    @MethodSource("createParamsProvider")
    public void testMapCreateIngredient(boolean isPublic, UUID owner) {
        UUID uuid = UUID.randomUUID();
        CreateIngredient createIngredient = new CreateIngredient()
                .descriptions(List.of(
                        new TranslatedString().language("de").translation("Gruß"),
                        new TranslatedString().language("en").translation("Greeting")))
                .names(List.of(
                        new TranslatedString().language("de").translation("Hallo"),
                        new TranslatedString().language("en").translation("Hello")))
                .typeId(uuid);

        DbCreateIngredient dbCreateIngredient = ingredientMapper.createIngredientToDbCreateIngredient(createIngredient, isPublic, owner);

        assertEquals(2, dbCreateIngredient.getDescriptions().size(), "Descriptions should be present");
        assertEquals(2, dbCreateIngredient.getNames().size(), "Names should be present");
        assertEquals(uuid, dbCreateIngredient.getTypeId(), "Type ID should be mapped");
        assertEquals(owner, dbCreateIngredient.getOwner(), "Owner UUID should match");
        assertEquals(isPublic, dbCreateIngredient.isPublished(), "Public status should match");
    }

    private static Stream<Arguments> createParamsProvider() {
        return Stream.of(
                Arguments.of(true, null),
                Arguments.of(false, UUID.randomUUID())
        );
    }

    @Test
    public void testMapUpdateIngredient() {
        UUID uuid = UUID.randomUUID();
        CreateIngredient createIngredient = new CreateIngredient()
                .descriptions(List.of(
                        new TranslatedString().language("de").translation("Gruß"),
                        new TranslatedString().language("en").translation("Greeting")))
                .names(List.of(
                        new TranslatedString().language("de").translation("Hallo"),
                        new TranslatedString().language("en").translation("Hello")))
                .typeId(uuid);

        DbUpdateIngredient updateIngredient = ingredientMapper.createIngredientToDbUpdateIngredient(createIngredient);
        assertEquals(2, updateIngredient.getDescriptions().size(), "Descriptions should be present");
        assertEquals(2, updateIngredient.getNames().size(), "Names should be present");
        assertEquals(uuid, updateIngredient.getTypeId(), "Type ID should be mapped");
    }
}
