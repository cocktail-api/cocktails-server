package de.slevermann.cocktails.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.IngredientsApi;
import de.slevermann.cocktails.models.Ingredient;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

public class IngredientsController implements IngredientsApi {
    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<List<Ingredient>> createBulkIngredients(@Valid List<Ingredient> body) {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> createIngredient(@Valid Ingredient body) {
        return null;
    }

    @Override
    public ResponseEntity<Void> deleteIngredient(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> getAllIngredients() {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> getIngredientById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> updateIngredient(@Valid Ingredient body) {
        return null;
    }
}
