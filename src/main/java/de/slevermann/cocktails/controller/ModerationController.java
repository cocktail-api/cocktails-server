package de.slevermann.cocktails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.ModerationApi;
import de.slevermann.cocktails.dto.LocalizedIngredient;
import de.slevermann.cocktails.dto.Moderation;
import de.slevermann.cocktails.service.ModerationService;
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
public class ModerationController implements ModerationApi {

    private final ModerationService moderationService;

    private final HttpServletRequest request;

    public ModerationController(ModerationService moderationService, HttpServletRequest request) {
        this.moderationService = moderationService;
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
    @PreAuthorize("hasAnyAuthority('cocktail-admins', 'cocktail-curators')")
    public ResponseEntity<List<LocalizedIngredient>> getIngredientQueue() {
        return ResponseEntity.ok(moderationService.getIngredientQueue(request.getLocale()));
    }

    @Override
    @PreAuthorize("hasAnyAuthority('cocktail-admins', 'cocktail-curators')")
    public ResponseEntity<Void> moderateIngredient(UUID id, @Valid Moderation body) {
        moderationService.moderateIngredient(id, body);
        return ResponseEntity.noContent().build();
    }
}
