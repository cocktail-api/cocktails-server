package de.slevermann.cocktails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.SearchApi;
import de.slevermann.cocktails.dto.SearchResult;
import de.slevermann.cocktails.service.SearchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SearchController implements SearchApi {

    private final SearchService searchService;

    private final HttpServletRequest httpServletRequest;

    public SearchController(SearchService searchService, HttpServletRequest httpServletRequest) {
        this.searchService = searchService;
        this.httpServletRequest = httpServletRequest;
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
    public ResponseEntity<List<SearchResult>> search(@NotNull @Valid String q) {
        return ResponseEntity.ok(searchService.search(q, httpServletRequest.getLocales()));
    }
}
