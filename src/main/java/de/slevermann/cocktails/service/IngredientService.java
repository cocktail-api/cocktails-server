package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.mapper.IngredientMapper;
import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.Ingredient;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
@Validated
public class IngredientService {

    private final IngredientDao ingredientDao;

    private final IngredientMapper ingredientMapper;

    private final AuthenticationService authenticationService;

    public IngredientService(IngredientDao ingredientDao, IngredientMapper ingredientMapper, AuthenticationService authenticationService) {
        this.ingredientDao = ingredientDao;
        this.ingredientMapper = ingredientMapper;
        this.authenticationService = authenticationService;
    }

    public List<LocalizedIngredient> getAll(Enumeration<Locale> locales) {
        return ingredientDao.getAll(locales.nextElement().getLanguage());
    }

    public Ingredient getById(UUID id) {
        DbIngredient dbIngredient = ingredientDao.getById(id);
        if (dbIngredient == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return ingredientMapper.dbIngredientToIngredient(dbIngredient);
    }

    public Ingredient createIngredient(CreateIngredient ingredient) {
        UUID uuid = authenticationService.getUserDetails().getUuid();
        return ingredientMapper.dbIngredientToIngredient(ingredientDao
                .create(ingredientMapper.createIngredientToDbCreateIngredient(ingredient, false, uuid)));
    }

    public Ingredient createAdminIngredient(CreateIngredient ingredient) {
        return ingredientMapper.dbIngredientToIngredient(ingredientDao
                .create(ingredientMapper.createIngredientToDbCreateIngredient(ingredient, true, null)));
    }

    public Ingredient updateIngredient(CreateIngredient ingredient, UUID id) {
        DbIngredient updated = ingredientDao.update(id, ingredientMapper.createIngredientToDbUpdateIngredient(ingredient));
        if (updated == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return ingredientMapper.dbIngredientToIngredient(updated);
    }

    public void deleteIngredient(UUID id) {
        int rowsAffected = ingredientDao.delete(id);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    public void setPublicStatus(UUID id, boolean publicStatus) {
        int rowsAffected = ingredientDao.setPublicStatus(id, publicStatus);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
