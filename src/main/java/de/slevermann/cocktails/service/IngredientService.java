package de.slevermann.cocktails.service;

import de.slevermann.cocktails.config.LanguageConfiguration;
import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dao.IngredientTypeDao;
import de.slevermann.cocktails.exception.BadTranslationException;
import de.slevermann.cocktails.exception.MissingIngredientTypeException;
import de.slevermann.cocktails.model.db.DbCreateIngredient;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.mapper.IngredientMapper;
import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.Ingredient;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.model.db.DbUpdateIngredient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

import static de.slevermann.cocktails.exception.BadTranslationException.Reason.*;
import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
@Validated
public class IngredientService {

    private final IngredientDao ingredientDao;

    private final IngredientTypeDao ingredientTypeDao;

    private final IngredientMapper ingredientMapper;

    private final AuthenticationService authenticationService;

    private final LanguageConfiguration languageConfiguration;

    public IngredientService(IngredientDao ingredientDao, IngredientTypeDao ingredientTypeDao, IngredientMapper ingredientMapper, AuthenticationService authenticationService, LanguageConfiguration languageConfiguration) {
        this.ingredientDao = ingredientDao;
        this.ingredientTypeDao = ingredientTypeDao;
        this.ingredientMapper = ingredientMapper;
        this.authenticationService = authenticationService;
        this.languageConfiguration = languageConfiguration;
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
        DbCreateIngredient dbCreateIngredient = ingredientMapper.createIngredientToDbCreateIngredient(ingredient,
                false, uuid);

        return createIngredient(dbCreateIngredient);
    }

    public Ingredient createAdminIngredient(CreateIngredient ingredient) {
        DbCreateIngredient dbCreateIngredient = ingredientMapper.createIngredientToDbCreateIngredient(ingredient,
                true, null);

        return createIngredient(dbCreateIngredient);
    }

    public Ingredient updateIngredient(CreateIngredient ingredient, UUID id) {
        DbUpdateIngredient dbUpdateIngredient = ingredientMapper.createIngredientToDbUpdateIngredient(ingredient);

        if (!dbUpdateIngredient.getNames().keySet()
                .equals(dbUpdateIngredient.getDescriptions().keySet())) {
            throw new BadTranslationException(MISMATCH);
        }

        verifyLanguages(dbUpdateIngredient.getNames().keySet());

        verifyType(dbUpdateIngredient.getTypeId());

        DbIngredient updated = ingredientDao.update(id, dbUpdateIngredient);
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

    private void verifyType(UUID uuid) {
        if (!ingredientTypeDao.exists(uuid)) {
            throw new MissingIngredientTypeException(uuid);
        }
    }

    private void verifyLanguages(Set<String> languages) {
        Set<String> supportedLanguages = languageConfiguration.getSupportedLanguages();

        for (String s : languages) {
            if (!supportedLanguages.contains(s)) {
                throw new BadTranslationException(s);
            }
        }
    }

    private Ingredient createIngredient(DbCreateIngredient dbCreateIngredient) {
        if (!dbCreateIngredient.getNames().keySet()
                .equals(dbCreateIngredient.getDescriptions().keySet())) {
            throw new BadTranslationException(MISMATCH);
        }

        if (dbCreateIngredient.getNames().isEmpty()) {
            throw new BadTranslationException(EMPTY);
        }

        verifyLanguages(dbCreateIngredient.getNames().keySet());

        verifyType(dbCreateIngredient.getTypeId());

        return ingredientMapper.dbIngredientToIngredient(ingredientDao.create(dbCreateIngredient));
    }
}
