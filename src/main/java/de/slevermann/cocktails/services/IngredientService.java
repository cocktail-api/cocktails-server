package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.dbmodels.DbCreateIngredient;
import de.slevermann.cocktails.dbmodels.DbIngredient;
import de.slevermann.cocktails.mapper.IngredientMapper;
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

    private final IngredientMapper ingredientMapper;

    public IngredientService(IngredientDao ingredientDao, IngredientMapper ingredientMapper) {
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
    }

    public List<LocalizedIngredient> getAll(Enumeration<Locale> locales) {
        return ingredientDao.getAll(locales.nextElement().getLanguage());
    }

    public Ingredient getById(UUID id) {
        DbIngredient dbIngredient = ingredientDao.getById(id);
        if (dbIngredient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredientMapper.dbIngredientToIngredient(dbIngredient);
    }

    public Ingredient createIngredient(CreateIngredient ingredient) {
        return ingredientMapper.dbIngredientToIngredient(ingredientDao
                .create(ingredientMapper.createIngredientToDbCreateIngredient(ingredient)));
    }

    public Ingredient updateIngredient(CreateIngredient ingredient, UUID id) {
        DbIngredient updated = ingredientDao.update(id, ingredientMapper.createIngredientToDbCreateIngredient(ingredient));
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredientMapper.dbIngredientToIngredient(updated);
    }

    public void deleteIngredient(UUID id) {
        long rowsAffected = ingredientDao.delete(id);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
