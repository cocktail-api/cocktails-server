package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.models.CreateIngredient;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.LocalizedIngredient;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@Validated
public class IngredientService {

    private final IngredientDao ingredientDao;

    public IngredientService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public List<LocalizedIngredient> getAll(Enumeration<Locale> locales) {
        return ingredientDao.getAll(locales.nextElement().getLanguage());
    }

    public Ingredient getById(Long id) {
        Ingredient ingredient = ingredientDao.getById(id);
        if (ingredient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredient;
    }

    public Ingredient createIngredient(CreateIngredient ingredient) {
        Long insertedId = ingredientDao.create(ingredient);

        if (insertedId != null) {
            Ingredient result = ingredientDao.getById(insertedId);
            if (result != null) {
                return result;
            }
        }
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unknown error when inserting entity");
    }

    public Ingredient updateIngredient(CreateIngredient ingredient, Long id) {
        long rowsAffected = ingredientDao.update(id, ingredient);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredientDao.getById(id);
    }
}
