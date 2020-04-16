package de.slevermann.cocktails.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.IngredientsApi;
import de.slevermann.cocktails.models.CreateIngredient;
import de.slevermann.cocktails.models.GetIngredient;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class IngredientController implements IngredientsApi {

    private final IngredientService ingredientService;

    private final HttpServletRequest request;

    public IngredientController(IngredientService ingredientService, HttpServletRequest request) {
        this.ingredientService = ingredientService;
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
    public ResponseEntity<GetIngredient> createNewIngredient(@Valid CreateIngredient body) {
        return null;
    }

    @Override
    public ResponseEntity<GetIngredient> getIngredientById(Long id) {
        List<String> locales = Collections.list(request.getLocales()).stream().map(Locale::toLanguageTag).collect(Collectors.toList());

        return ResponseEntity.ok(ingredientService.getIngredientById(id, locales));
    }

    @Override
    public ResponseEntity<List<IngredientType>> getIngredientTypes() {
        return ResponseEntity.ok(ingredientService.getTypes());
    }
}
