package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.dto.Moderation;
import de.slevermann.cocktails.exception.ModerationException;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.db.DbModeration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@MockitoSettings
public class ModerationServiceTest {

    private final IngredientDao ingredientDao = mock(IngredientDao.class);

    private final ModerationService moderationService = new ModerationService(ingredientDao);

    private static final LocalizedIngredientType TYPE = new LocalizedIngredientType()
            .type("typ")
            .language("en")
            .id(UUID.randomUUID());

    private static final List<LocalizedIngredient> QUEUE = List.of(
            new LocalizedIngredient()
                    .id(UUID.randomUUID())
                    .type(TYPE)
                    .language("en")
                    .name("name")
                    .description("description"),
            new LocalizedIngredient()
                    .id(UUID.randomUUID())
                    .type(TYPE)
                    .language("en")
                    .name("name")
                    .description("description")
    );

    private static final DbIngredientType DB_TYPE = DbIngredientType.builder()
            .names(Map.of("de", "bla"))
            .uuid(UUID.randomUUID()).build();

    private static DbIngredient buildIngredient(DbModeration moderation) {
        return DbIngredient.builder()
                .uuid(UUID.randomUUID())
                .type(DB_TYPE)
                .moderation(moderation)
                .names(Map.of("de", "bla"))
                .descriptions(Map.of("de", "foo")).build();
    }

    @Test
    public void testGetIngredientQueue() {
        when(ingredientDao.getModerationQueue(anyString())).thenReturn(QUEUE);

        assertEquals(QUEUE, moderationService.getIngredientQueue(Locale.GERMAN),
                "Service list should be equal to what the dao returns");
    }

    @ParameterizedTest
    @EnumSource(Moderation.ActionEnum.class)
    public void testModerateIngredientNotFound(Moderation.ActionEnum action) {
        when(ingredientDao.getById(any())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> moderationService.moderateIngredient(UUID.randomUUID(), new Moderation().action(action)),
                "Moderating a non existing ingredient should throw an exception");

        assertEquals(NOT_FOUND, ex.getStatus(), "HTTP Status should be 404");
    }

    @ParameterizedTest
    @EnumSource(Moderation.ActionEnum.class)
    public void testModerateNoStatus(Moderation.ActionEnum action) {
        when(ingredientDao.getById(any())).thenReturn(buildIngredient(null));

        assertThrows(ModerationException.class,
                () -> moderationService.moderateIngredient(UUID.randomUUID(), new Moderation().action(action)),
                "Missing status in db should throw an exception");
    }

    @ParameterizedTest
    @EnumSource(Moderation.ActionEnum.class)
    public void testModerateRejected(Moderation.ActionEnum action) {
        when(ingredientDao.getById(any())).thenReturn(buildIngredient(DbModeration.REJECTED));

        ModerationException ex = assertThrows(ModerationException.class,
                () -> moderationService.moderateIngredient(UUID.randomUUID(), new Moderation().action(action)),
                "Moderating a rejected ingredient should throw an exception");
        assertTrue(ex.getMessage().contains("rejected"), "Exception message contains error cause");
    }

    @ParameterizedTest
    @EnumSource(Moderation.ActionEnum.class)
    public void testModerateWaiting(Moderation.ActionEnum action) {
        when(ingredientDao.getById(any())).thenReturn(buildIngredient(DbModeration.WAITING));

        moderationService.moderateIngredient(UUID.randomUUID(), new Moderation().action(action));
    }
}
