package de.slevermann.cocktails.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.IngredientsApi;
import de.slevermann.cocktails.models.GetIngredient;
import de.slevermann.cocktails.services.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class IngredientsController implements IngredientsApi {

    private final IngredientService ingredientService;

    private final HttpServletRequest request;

    public IngredientsController(IngredientService ingredientService, HttpServletRequest request) {
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
    public ResponseEntity<GetIngredient> getIngredientById(Long id) {
        List<String> locales = Collections.list(request.getLocales()).stream().map(Locale::toLanguageTag).collect(Collectors.toList());

        return ResponseEntity.ok(ingredientService.getIngredientById(id, locales));
    }
}
