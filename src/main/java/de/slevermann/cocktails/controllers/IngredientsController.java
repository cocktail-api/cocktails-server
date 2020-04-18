package de.slevermann.cocktails.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.IngredientsApi;
import de.slevermann.cocktails.models.*;
import de.slevermann.cocktails.services.IngredientTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class IngredientsController implements IngredientsApi {

    private final IngredientTypeService ingredientTypeService;
    private final HttpServletRequest request;

    public IngredientsController(IngredientTypeService ingredientTypeService, HttpServletRequest request) {
        this.ingredientTypeService = ingredientTypeService;
        this.request = request;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    public ResponseEntity<Ingredient> createIngredient(@Valid CreateIngredient body) {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> getIngredientById(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<IngredientType> getIngredientTypeById(Long id) {
        return ResponseEntity.ok(ingredientTypeService.getById(id));
    }

    @Override
    public ResponseEntity<List<LocalizedIngredientType>> getIngredientTypes() {
        return ResponseEntity.ok(ingredientTypeService.getAll(request.getLocales()));
    }

    @Override
    public ResponseEntity<List<LocalizedIngredient>> getIngredients() {
        return null;
    }

    @Override
    public ResponseEntity<Ingredient> updateIngredient(@Valid CreateIngredient body, Long id) {
        return null;
    }
}
