package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientTypeDao;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.mapper.IngredientTypeMapper;
import de.slevermann.cocktails.dto.IngredientType;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
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
public class IngredientTypeService {

    private final IngredientTypeDao ingredientTypeDao;

    private final IngredientTypeMapper ingredientTypeMapper;

    public IngredientTypeService(IngredientTypeDao ingredientTypeDao, IngredientTypeMapper ingredientTypeMapper) {
        this.ingredientTypeDao = ingredientTypeDao;
        this.ingredientTypeMapper = ingredientTypeMapper;
    }

    public List<LocalizedIngredientType> getAll(Enumeration<Locale> locales) {
        return ingredientTypeDao.getAll(locales.nextElement().getLanguage());
    }

    public IngredientType getById(UUID id) {
        DbIngredientType type = ingredientTypeDao.getById(id);
        if (type == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ingredientTypeMapper.dbIngredientTypetoIngredientType(type);
    }
}
