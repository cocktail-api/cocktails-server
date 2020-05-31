package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientTypeDao;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.mapper.IngredientTypeMapper;
import de.slevermann.cocktails.mapper.TranslatedStringMapper;
import de.slevermann.cocktails.model.db.DbIngredientType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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
public class IngredientTypeServiceTest {

    private final IngredientTypeDao ingredientTypeDao = mock(IngredientTypeDao.class);

    public IngredientTypeService ingredientTypeService = new IngredientTypeService(
            ingredientTypeDao, new IngredientTypeMapper(new TranslatedStringMapper())
    );


    @Test
    public void testGetByIdFound() {
        UUID uuid = UUID.randomUUID();
        when(ingredientTypeDao.getById(uuid)).thenReturn(DbIngredientType.builder()
                .uuid(uuid)
                .names(Map.of("de", "hallo", "en", "hello")).build());
        assertNotNull(ingredientTypeService.getById(uuid), "Service should return an object if the dao finds one");
    }

    @Test
    public void testGetByIdNotFound() {
        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> ingredientTypeService.getById(UUID.randomUUID()),
                "Service should throw an exception if the ID can't be found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Exception status should be 404");
    }

    @ParameterizedTest
    @MethodSource("getTypes")
    public void testGetAll(List<LocalizedIngredientType> result) {
        when(ingredientTypeDao.getAll(anyString())).thenReturn(result);
        assertEquals(result, ingredientTypeService.getAll(Collections.enumeration(List.of(new Locale("de")))));
    }

    public static Stream<Arguments> getTypes() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(new LocalizedIngredientType().language("de").type("bla"))),
                Arguments.of(List.of(new LocalizedIngredientType().language("de").type("bla"), new LocalizedIngredientType()))
        );
    }
}
