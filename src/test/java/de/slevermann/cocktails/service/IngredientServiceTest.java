package de.slevermann.cocktails.service;

import de.slevermann.cocktails.config.LanguageConfiguration;
import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dao.IngredientTypeDao;
import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.dto.TranslatedString;
import de.slevermann.cocktails.exception.BadTranslationException;
import de.slevermann.cocktails.exception.MissingIngredientTypeException;
import de.slevermann.cocktails.exception.ModerationException;
import de.slevermann.cocktails.mapper.IngredientMapper;
import de.slevermann.cocktails.mapper.IngredientTypeMapper;
import de.slevermann.cocktails.mapper.TranslatedStringMapper;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.db.DbModeration;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@MockitoSettings
public class IngredientServiceTest {

    private static final DbIngredientType TYPE_ONE = DbIngredientType.builder()
            .uuid(UUID.randomUUID())
            .names(Map.of()).build();

    private static final DbIngredient INGREDIENT_ONE = DbIngredient.builder()
            .uuid(UUID.randomUUID())
            .names(Map.of())
            .descriptions(Map.of())
            .published(true)
            .type(TYPE_ONE).build();

    private static final CreateIngredient CREATE_INGREDIENT = new CreateIngredient()
            .typeId(UUID.randomUUID())
            .names(List.of(new TranslatedString().language("de").translation("hallo")))
            .descriptions(List.of(new TranslatedString().language("de").translation("hallo welt")));

    private static final CreateIngredient CREATE_MISMATCH = new CreateIngredient()
            .typeId(UUID.randomUUID())
            .names(List.of(new TranslatedString().language("en").translation("hello")))
            .descriptions(List.of(new TranslatedString().language("de").translation("hallo welt")));

    private static final CreateIngredient CREATE_EMPTY = new CreateIngredient()
            .typeId(UUID.randomUUID())
            .names(List.of())
            .descriptions(List.of());

    private static final CreateIngredient CREATE_INVALID = new CreateIngredient()
            .typeId(UUID.randomUUID())
            .names(List.of(new TranslatedString().language("badlanguage").translation("foo")))
            .descriptions(List.of(new TranslatedString().language("badlanguage").translation("bar")));

    private static final LocalizedIngredientType LOCALIZED_TYPE = new LocalizedIngredientType()
            .type("typ")
            .language("de")
            .id(UUID.randomUUID());

    private static final LocalizedIngredient LOCALIZED_INGREDIENT = new LocalizedIngredient()
            .language("de")
            .name("name")
            .description("beschreibung")
            .id(UUID.randomUUID())
            .type(LOCALIZED_TYPE);

    private static final DbUserInfo OWNER = DbUserInfo.builder()
            .nick("john.doe")
            .providerId("providerId")
            .uuid(UUID.randomUUID()).build();

    private static final DbIngredient OWNED_INGREDIENT = DbIngredient.builder()
            .uuid(UUID.randomUUID())
            .names(Map.of())
            .descriptions(Map.of())
            .published(false)
            .userInfo(OWNER)
            .type(TYPE_ONE).build();

    private final IngredientDao ingredientDao = mock(IngredientDao.class);

    private final IngredientTypeDao ingredientTypeDao = mock(IngredientTypeDao.class);

    private final TranslatedStringMapper translatedStringMapper = new TranslatedStringMapper();

    private final IngredientMapper ingredientMapper = spy(new IngredientMapper(
            new UserInfoMapper(), new IngredientTypeMapper(translatedStringMapper), translatedStringMapper
    ));

    private final AuthenticationService authenticationService = mock(AuthenticationService.class);

    private final LanguageConfiguration languageConfiguration = new LanguageConfiguration();

    private final IngredientService ingredientService = new IngredientService(ingredientDao, ingredientTypeDao, ingredientMapper, authenticationService, languageConfiguration);

    private static Stream<Arguments> getIngredients() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(LOCALIZED_INGREDIENT)),
                Arguments.of(List.of(LOCALIZED_INGREDIENT, new LocalizedIngredient()))
        );
    }

    @Test
    public void testGetById() {
        when(ingredientDao.getById(any())).thenReturn(INGREDIENT_ONE);

        assertNotNull(ingredientService.getById(UUID.randomUUID()),
                "Ingredient should not be null when dao returns a valid ingredient");

        verify(ingredientMapper, times(1)).dbIngredientToIngredient(any());
    }

    @Test
    public void testGetByIdNotFound() {
        when(ingredientDao.getById(any())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.getById(UUID.randomUUID()),
                "Service should throw an exception if an ingredient can't be found");

        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
    }

    @Test
    public void testDelete() {
        when(ingredientDao.delete(any())).thenReturn(1);

        ingredientService.deleteIngredient(UUID.randomUUID());
    }

    @Test
    public void testDeleteNotFound() {
        when(ingredientDao.delete(any())).thenReturn(0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.deleteIngredient(UUID.randomUUID()),
                "Service should throw an exception if ingredient for deletion can't be found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testSetPublicStatus(boolean status) {
        when(ingredientDao.setPublicStatus(any(), anyBoolean())).thenReturn(1);

        ingredientService.setPublicStatus(UUID.randomUUID(), status);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    public void testSetPublicStatusNotFound(boolean status) {
        when(ingredientDao.setPublicStatus(any(), anyBoolean())).thenReturn(0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.setPublicStatus(UUID.randomUUID(), status),
                "Service should throw an exception if ingredient for status update can't be found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
    }

    @Test
    public void testUpdateIngredient() {
        when(ingredientDao.update(any(), any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(true);

        assertNotNull(ingredientService.updateIngredient(CREATE_INGREDIENT, UUID.randomUUID()),
                "Updated ingredient returned should not be null");

        verify(ingredientMapper, times(1)).dbIngredientToIngredient(any());
    }

    @Test
    public void testUpdateIngredientNotFound() {
        when(ingredientDao.update(any(), any())).thenReturn(null);
        when(ingredientTypeDao.exists(any())).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.updateIngredient(CREATE_INGREDIENT, UUID.randomUUID()),
                "Service should throw an exception when ingredient to update isn't found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
    }

    @Test
    public void testUpdateIngredientMismatch() {
        assertThrows(BadTranslationException.class,
                () -> ingredientService.updateIngredient(CREATE_MISMATCH, UUID.randomUUID()),
                "A translation mismatch should throw an exception");
    }

    @Test
    public void testUpdateIngredientEmpty() {
        when(ingredientDao.update(any(), any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(true);
        assertNotNull(ingredientService.updateIngredient(CREATE_EMPTY, UUID.randomUUID()),
                "Updated ingredient returned should not be null");

        verify(ingredientMapper, times(1)).dbIngredientToIngredient(any());
    }

    @Test
    public void testUpdateInvalidLanguage() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);

        assertThrows(BadTranslationException.class,
                () -> ingredientService.updateIngredient(CREATE_INVALID, UUID.randomUUID()),
                "An invalid language should throw an exception");
    }

    @Test
    public void testUpdateTypeNotFound() {
        when(ingredientDao.update(any(), any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(false);

        assertThrows(MissingIngredientTypeException.class,
                () -> ingredientService.updateIngredient(CREATE_INGREDIENT, UUID.randomUUID()),
                "Missing ingredient type should throw an exception");
    }

    @Test
    public void testCreateIngredient() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());
        when(ingredientTypeDao.exists(any())).thenReturn(true);

        ingredientService.createIngredient(CREATE_INGREDIENT);
    }

    @Test
    public void testCreateMismatch() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createIngredient(CREATE_MISMATCH),
                "A translation mismatch should throw an exception");
    }

    @Test
    public void testCreateEmpty() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createIngredient(CREATE_EMPTY),
                "An empty translation should throw an exception");
    }

    @Test
    public void testCreateInvalidLanguage() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createIngredient(CREATE_INVALID),
                "An invalid language should throw an exception");
    }

    @Test
    public void testCreateTypeNotFound() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(false);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());

        assertThrows(MissingIngredientTypeException.class,
                () -> ingredientService.createIngredient(CREATE_INGREDIENT),
                "Missing ingredient type should throw an exception");
    }

    @Test
    public void testCreateAdminIngredient() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(true);

        ingredientService.createAdminIngredient(CREATE_INGREDIENT);
    }

    @Test
    public void testCreateAdminMismatch() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createAdminIngredient(CREATE_MISMATCH),
                "A translation mismatch should throw an exception");
    }

    @Test
    public void testCreateAdminEmpty() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createAdminIngredient(CREATE_EMPTY),
                "An empty should throw an exception");
    }

    @Test
    public void testCreateAdminInvalidLanguage() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);

        assertThrows(BadTranslationException.class,
                () -> ingredientService.createAdminIngredient(CREATE_INVALID),
                "An invalid language should throw an exception");
    }

    @Test
    public void testCreateAdminTypeNotFound() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(ingredientTypeDao.exists(any())).thenReturn(false);

        assertThrows(MissingIngredientTypeException.class,
                () -> ingredientService.createAdminIngredient(CREATE_INGREDIENT),
                "Missing ingredient type should throw an exception");
    }

    @ParameterizedTest
    @MethodSource("getIngredients")
    public void testAll(List<LocalizedIngredient> ingredients) {
        when(ingredientDao.getAll(anyString()))
                .thenReturn(ingredients);

        assertEquals(ingredients, ingredientService.getAll(Collections.enumeration(List.of(new Locale("de")))));
    }

    @Test
    public void testSubmit() {
        when(ingredientDao.getById(any())).thenReturn(OWNED_INGREDIENT);
        when(authenticationService.getUserDetails()).thenReturn(OWNER);

        ingredientService.submit(OWNED_INGREDIENT.getUuid());
    }

    @Test
    public void testSubmitNotFound() {
        when(ingredientDao.getById(any())).thenReturn(null);
        when(authenticationService.getUserDetails()).thenReturn(OWNER);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.submit(OWNED_INGREDIENT.getUuid()),
                "Ingredient not found should throw an error");
        assertEquals(NOT_FOUND, ex.getStatus(), "Status should be 404");
    }

    @Test
    public void testSubmitWrongUser() {
        when(ingredientDao.getById(any())).thenReturn(OWNED_INGREDIENT);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId").build());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.submit(OWNED_INGREDIENT.getUuid()),
                "Ingredient not found should throw an error");
        assertEquals(NOT_FOUND, ex.getStatus(), "Status should be 404");
    }

    @ParameterizedTest
    @EnumSource(DbModeration.class)
    public void testSubmitModerated(DbModeration moderation) {
        DbIngredient moderatedIngredient = DbIngredient.builder()
                .uuid(UUID.randomUUID())
                .names(Map.of())
                .descriptions(Map.of())
                .published(false)
                .userInfo(OWNER)
                .type(TYPE_ONE)
                .moderation(moderation).build();
        when(ingredientDao.getById(any())).thenReturn(moderatedIngredient);
        when(authenticationService.getUserDetails()).thenReturn(OWNER);

        assertThrows(ModerationException.class, () -> ingredientService.submit(OWNED_INGREDIENT.getUuid()),
                "Submitting a moderated ingredient should throw an error");
    }

    @Test
    public void testSubmitPublished() {
        DbIngredient publicIngredient = DbIngredient.builder()
                .uuid(UUID.randomUUID())
                .names(Map.of())
                .descriptions(Map.of())
                .published(true)
                .userInfo(OWNER)
                .type(TYPE_ONE).build();
        when(ingredientDao.getById(any())).thenReturn(publicIngredient);
        when(authenticationService.getUserDetails()).thenReturn(OWNER);

        assertThrows(ModerationException.class, () -> ingredientService.submit(OWNED_INGREDIENT.getUuid()),
                "Submitting a published ingredient should throw an error");
    }
}
