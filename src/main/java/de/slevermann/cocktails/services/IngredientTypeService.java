package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientTypeDao;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.LocalizedIngredientType;
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
public class IngredientTypeService {

    private final IngredientTypeDao ingredientTypeDao;

    public IngredientTypeService(IngredientTypeDao ingredientTypeDao) {
        this.ingredientTypeDao = ingredientTypeDao;
    }

    public List<LocalizedIngredientType> getAll(Enumeration<Locale> locales) {
        return ingredientTypeDao.getAll(locales.nextElement().getLanguage());
    }

    public IngredientType getById(Long id) {
        IngredientType itype = ingredientTypeDao.getById(id);
        if (itype == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredientTypeDao.getById(id);
    }
}
