package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.daos.IngredientTypeDao;
import de.slevermann.cocktails.models.GetIngredient;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Validated
public class IngredientService {

    private final IngredientDao ingredientDao;

    private final IngredientTypeDao ingredientTypeDao;

    private final Logger logger = LoggerFactory.getLogger(IngredientService.class);

    public IngredientService(IngredientDao ingredientDao, IngredientTypeDao ingredientTypeDao) {
        this.ingredientDao = ingredientDao;
        this.ingredientTypeDao = ingredientTypeDao;
    }

    public GetIngredient getIngredientById(Long id, List<String> preferredLocales) {
        List<Locale> availableLocales = ingredientDao.findLocalesForIngredient(id);
        if (availableLocales.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ingredients found for ID: " + id);
        }
        Locale bestLocale = getBestLocale(availableLocales, preferredLocales);

        return ingredientDao.findByIdAndLocale(id, bestLocale.getLanguage(), bestLocale.getCountry());
    }

    public List<IngredientType> getTypes() {
        return ingredientTypeDao.findAll();
    }

    private Locale getBestLocale(List<Locale> availableLocales, List<String> preferredLocales) {
        for (String preferredLocale : preferredLocales) {
            Optional<Locale> result = availableLocales.stream().filter(locale -> {
                String tuple = locale.getLanguage() + "-" + locale.getCountry();
                return tuple.equals(preferredLocale) || tuple.startsWith(preferredLocale);
            }).findFirst();
            if (result.isPresent()) {
                return result.get();
            }
        }

        // When we get here, we didn't find any matching locale in the user's preference list.
        for (String fallbackLocale : new String[]{"en", "de"}) {
            Optional<Locale> maybeLocale = availableLocales.stream().filter(l -> l.getLanguage().equals(fallbackLocale)).findFirst();
            if (maybeLocale.isPresent()) {
                logger.info("Failed to find preferred locales. Falling back to {}", fallbackLocale);
                return maybeLocale.get();
            }
        }
        Locale result = availableLocales.get(0);
        logger.info("Failed to find any preferred or fallback locales. Falling back to the first available locale {}", result);
        return result;
    }
}
