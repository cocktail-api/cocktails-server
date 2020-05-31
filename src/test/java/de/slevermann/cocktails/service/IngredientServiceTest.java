package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.mapper.IngredientMapper;
import de.slevermann.cocktails.mapper.IngredientTypeMapper;
import de.slevermann.cocktails.mapper.TranslatedStringMapper;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
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
            .isPublic(true)
            .type(TYPE_ONE).build();

    private static final CreateIngredient CREATE_INGREDIENT = new CreateIngredient()
            .typeId(UUID.randomUUID())
            .names(List.of())
            .descriptions(List.of());
    public static final LocalizedIngredientType LOCALIZED_TYPE = new LocalizedIngredientType()
            .type("typ")
            .language("de")
            .id(UUID.randomUUID());
    public static final LocalizedIngredient LOCALIZED_INGREDIENT = new LocalizedIngredient()
            .language("de")
            .name("name")
            .description("beschreibung")
            .id(UUID.randomUUID())
            .type(LOCALIZED_TYPE);

    private final IngredientDao ingredientDao = mock(IngredientDao.class);

    private final TranslatedStringMapper translatedStringMapper = new TranslatedStringMapper();

    private final IngredientMapper ingredientMapper = spy(new IngredientMapper(
            new UserInfoMapper(), new IngredientTypeMapper(translatedStringMapper), translatedStringMapper
    ));

    private final AuthenticationService authenticationService = mock(AuthenticationService.class);

    private final IngredientService ingredientService = new IngredientService(ingredientDao, ingredientMapper, authenticationService);

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

        assertNotNull(ingredientService.updateIngredient(CREATE_INGREDIENT, UUID.randomUUID()),
                "Updated ingredient returned should not be null");

        verify(ingredientMapper, times(1)).dbIngredientToIngredient(any());
    }

    @Test
    public void testUpdateIngredientNotFound() {
        when(ingredientDao.update(any(), any())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientService.updateIngredient(CREATE_INGREDIENT, UUID.randomUUID()),
                "Service should throw an exception when ingredient to update isn't found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
    }

    @Test
    public void testCreateIngredient() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);
        when(authenticationService.getUserDetails()).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID())
                .providerId("providerId")
                .nick("john.doe").build());

        ingredientService.createIngredient(CREATE_INGREDIENT);
    }

    @Test
    public void testCreateAdminIngredient() {
        when(ingredientDao.create(any())).thenReturn(INGREDIENT_ONE);

        ingredientService.createAdminIngredient(CREATE_INGREDIENT);
    }

    @ParameterizedTest
    @MethodSource("getIngredients")
    public void testAll(List<LocalizedIngredient> ingredients) {
        when(ingredientDao.getAll(anyString()))
                .thenReturn(ingredients);

        assertEquals(ingredients, ingredientService.getAll(Collections.enumeration(List.of(new Locale("de")))));
    }
}
