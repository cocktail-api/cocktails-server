package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.Moderation;
import de.slevermann.cocktails.exception.ModerationException;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbModeration;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
@Validated
public class ModerationService {

    private final IngredientDao ingredientDao;

    public ModerationService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public List<LocalizedIngredient> getIngredientQueue(Locale locale) {
        return ingredientDao.getModerationQueue(locale.getLanguage());
    }


    public void moderateIngredient(UUID id, Moderation moderation) {
        DbIngredient ingredient = ingredientDao.getById(id);

        if (ingredient == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }

        DbModeration ingredientStatus = ingredient.getModeration();
        if (ingredientStatus == null) {
            throw new ModerationException(String.format("Ingredient %s is currently not awaiting moderation", id));
        }

        switch (ingredientStatus) {
            case WAITING -> {
                switch (moderation.getAction()) {
                    case ACCEPT -> ingredientDao.promote(id);
                    case REJECT -> ingredientDao.reject(id);
                }
            }
            case REJECTED -> throw new ModerationException(String.format("Ingredient %s is already rejected.", id));
        }
    }
}
