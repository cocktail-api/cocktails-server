package de.slevermann.cocktails.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.LocalesApi;
import de.slevermann.cocktails.models.Locale;
import de.slevermann.cocktails.services.LocaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
public class LocaleController implements LocalesApi {

    private final LocaleService localeService;

    public LocaleController(LocaleService localeService) {
        this.localeService = localeService;
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
    public ResponseEntity<List<Locale>> getLocales() {
        return ResponseEntity.ok(localeService.getAll());
    }
}
