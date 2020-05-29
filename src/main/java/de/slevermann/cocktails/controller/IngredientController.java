package de.slevermann.cocktails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.IngredientsApi;
import de.slevermann.cocktails.dto.CreateIngredient;
import de.slevermann.cocktails.dto.Ingredient;
import de.slevermann.cocktails.dto.IngredientType;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.LocalizedIngredientType;
import de.slevermann.cocktails.service.IngredientService;
import de.slevermann.cocktails.service.IngredientTypeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class IngredientController implements IngredientsApi {

    private final IngredientTypeService ingredientTypeService;

    private final IngredientService ingredientService;

    private final HttpServletRequest request;

    public IngredientController(IngredientTypeService ingredientTypeService, IngredientService ingredientService, HttpServletRequest request) {
        this.ingredientTypeService = ingredientTypeService;
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
    @PreAuthorize("hasAuthority('cocktail-admins')")
    public ResponseEntity<Ingredient> adminCreateIngredient(@Valid CreateIngredient body) {
        return ResponseEntity.ok(ingredientService.createAdminIngredient(body));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Ingredient> createIngredient(@Valid CreateIngredient body) {
        return ResponseEntity.ok(ingredientService.createIngredient(body));
    }

    @Override
    @PreAuthorize("hasAuthority('cocktail-admins')")
    public ResponseEntity<Void> deleteIngredient(UUID id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Ingredient> getIngredientById(UUID id) {
        return ResponseEntity.ok(ingredientService.getById(id));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('cocktail-admins', 'cocktail-curators')")
    public ResponseEntity<List<LocalizedIngredient>> getIngredientQueue() {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    public ResponseEntity<IngredientType> getIngredientTypeById(UUID id) {
        return ResponseEntity.ok(ingredientTypeService.getById(id));
    }

    @Override
    public ResponseEntity<List<LocalizedIngredientType>> getIngredientTypes() {
        return ResponseEntity.ok(ingredientTypeService.getAll(request.getLocales()));
    }

    @Override
    public ResponseEntity<List<LocalizedIngredient>> getIngredients() {
        return ResponseEntity.ok(ingredientService.getAll(request.getLocales()));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('cocktail-admins', 'cocktail-curators')")
    public ResponseEntity<Void> setIngredientPublicStatus(@Valid Boolean body, UUID id) {
        ingredientService.setPublicStatus(id, body);
        return ResponseEntity.ok().build();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> submitIngredient(UUID id) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('cocktail-admins', 'cocktail-curators')")
    public ResponseEntity<Ingredient> updateIngredient(@Valid CreateIngredient body, UUID id) {
        return ResponseEntity.ok(ingredientService.updateIngredient(body, id));
    }
}
