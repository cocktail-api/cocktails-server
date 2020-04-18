package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientTypeDao;
import de.slevermann.cocktails.models.LocalizedIngredientType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@Service
@Transactional
@Validated
public class IngredientTypeService {

    private final IngredientTypeDao ingredientTypeDao;

    public IngredientTypeService(IngredientTypeDao ingredientTypeDao) {
        this.ingredientTypeDao = ingredientTypeDao;
    }

    public List<LocalizedIngredientType> getAll(Enumeration<Locale> locales) {
        return ingredientTypeDao.getAll(locales.nextElement().getLanguage());
    }
}
