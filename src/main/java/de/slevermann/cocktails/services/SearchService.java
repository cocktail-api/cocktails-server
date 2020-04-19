package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.IngredientDao;
import de.slevermann.cocktails.models.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

@Service
public class SearchService {

    private final IngredientDao ingredientDao;

    public SearchService(IngredientDao ingredientDao) {
        this.ingredientDao = ingredientDao;
    }

    public List<SearchResult> search(String search, Enumeration<Locale> locales) {
        List<SearchResult> results = new ArrayList<>();
        results.addAll(searchIngredients(search, locales));
        results.addAll(searchCocktails(search, locales));
        return results;
    }

    private List<SearchResult> searchIngredients(String search, Enumeration<Locale> locales) {
        String query = escape(search);
        return ingredientDao.search("%" + query + "%", locales.nextElement().getLanguage());
    }

    private List<SearchResult> searchCocktails(String search, Enumeration<Locale> locales) {
        return new ArrayList<>();
    }

    /**
     * Escape string comparison operators and backslashes
     *
     * @param search input
     * @return escaped string
     */
    private static String escape(String search) {
        return search.replace("\\", "\\\\")
                .replace("%", "\\%")
                .replace("_", "\\_");
    }

}
