package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.models.Ingredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@Transactional
@Validated
public class IngredientService {

    private IngredientDao ingredientDao;

    public IngredientService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public Long createIngredient(@Valid Ingredient ingredient) {
        return ingredientDao.insert(ingredient);
    }
}
