package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientTypeDao;
import de.slevermann.cocktails.dbmodels.DbIngredientType;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.LocalizedIngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@MockitoSettings
public class IngredientTypeServiceTest {

    @Mock
    private IngredientTypeDao ingredientTypeDao;

    @InjectMocks
    private IngredientTypeService ingredientTypeService;

    private static Stream<Arguments> getIngredientTypeLists() {
        LocalizedIngredientType localizedIngredientTypeOne = new LocalizedIngredientType();
        localizedIngredientTypeOne.setId(1L);
        localizedIngredientTypeOne.setLanguage("en");
        localizedIngredientTypeOne.setType("Strong Liquor");

        LocalizedIngredientType localizedIngredientTypeTwo = new LocalizedIngredientType();
        localizedIngredientTypeTwo.setId(2L);
        localizedIngredientTypeTwo.setLanguage("en");
        localizedIngredientTypeTwo.setType("Fruit Juice");

        return Stream.of(
                Arguments.of(Collections.emptyList()),
                Arguments.of(List.of(localizedIngredientTypeOne)),
                Arguments.of(List.of(localizedIngredientTypeOne, localizedIngredientTypeTwo))
        );
    }

    @ParameterizedTest
    @MethodSource("getIngredientTypeLists")
    public void testGetAllIngredientTypes(List<LocalizedIngredientType> ingredientTypes) {
        when(ingredientTypeDao.getAll(anyString())).thenReturn(ingredientTypes);

        assertEquals(ingredientTypes, ingredientTypeService.getAll(Collections.enumeration(
                List.of(new Locale("en")))));
    }

    @Test
    public void testGetSingleIngredientType() {
        Map<String, String> names = new HashMap<>();
        names.put("de", "Deutscher name");
        names.put("en", "English name");
        DbIngredientType it = DbIngredientType.builder()
                .id(1L)
                .names(names).build();
        when(ingredientTypeDao.getById(anyLong())).thenReturn(it);

        IngredientType fromService = ingredientTypeService.getById(1L);

        assertEquals(it.toIngredientType(), fromService, "Returned object should equal input");
    }

    @Test
    public void testGetUnavailableIngredientType() {
        when(ingredientTypeDao.getById(anyLong())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> ingredientTypeService.getById(1L));

        assertEquals(HttpStatus.NOT_FOUND, ex.getStatus(), "Response status should be 404 NOT FOUND");
    }
}
