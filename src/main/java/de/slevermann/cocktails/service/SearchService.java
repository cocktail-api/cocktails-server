package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.model.SearchResult;
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
        return ingredientDao.search(search, locales.nextElement().getLanguage());
    }

    private List<SearchResult> searchCocktails(String search, Enumeration<Locale> locales) {
        return new ArrayList<>();
    }
}
