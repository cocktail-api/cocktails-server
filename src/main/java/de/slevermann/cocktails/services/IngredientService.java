package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.dbmodels.DbCreateIngredient;
import de.slevermann.cocktails.dbmodels.DbIngredient;
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
import java.util.UUID;

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

    public Ingredient getById(UUID id) {
        Ingredient ingredient = ingredientDao.getById(id).toIngredient();
        if (ingredient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredient;
    }

    public Ingredient createIngredient(CreateIngredient ingredient) {
        return ingredientDao.create(new DbCreateIngredient(ingredient)).toIngredient();
    }

    public Ingredient updateIngredient(CreateIngredient ingredient, UUID id) {
        DbIngredient updated = ingredientDao.update(id, new DbCreateIngredient(ingredient));
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return updated.toIngredient();
    }

    public void deleteIngredient(UUID id) {
        long rowsAffected = ingredientDao.delete(id);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
