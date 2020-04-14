package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.models.GetIngredient;
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

    private final Logger logger = LoggerFactory.getLogger(IngredientService.class);

    public IngredientService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public GetIngredient getIngredientById(Long id, List<String> preferredLocales) {
        List<String> availableLocales = ingredientDao.findLocalesForIngredient(id);
        if (availableLocales.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No ingredients found for ID: " + id);
        }
        String bestLocale = getBestLocale(availableLocales, preferredLocales);

        return ingredientDao.findByIdAndLocale(id, bestLocale);
    }

    private String getBestLocale(List<String> availableLocales, List<String> preferredLocales) {
        for (String preferredLocale : preferredLocales) {
            if (availableLocales.contains(preferredLocale)) {
                return preferredLocale;
            }
        }
        Optional<String> maybeEnglish = availableLocales.stream().filter(s -> s.startsWith("en")).findFirst();

        if (maybeEnglish.isPresent()) {
            String english = maybeEnglish.get();
            logger.info("Failed to find preferred locales. Falling back to English: " + english);
            return english;
        } else {
            Optional<String> maybeGerman = availableLocales.stream().filter(s -> s.startsWith("de")).findFirst();
            if (maybeGerman.isPresent()) {
                String german = maybeGerman.get();
                logger.info("Failed to find preferred locales. Falling back to German: " + german);
                return german;
            } else {
                String first = availableLocales.get(0);
                logger.info("Failed to find preferred locales. Falling back to first available locale: " + first);
                return first;
            }
        }
    }
}
