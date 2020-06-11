package de.slevermann.cocktails.controller;

import de.slevermann.cocktails.dto.SearchResult;
import de.slevermann.cocktails.service.SearchService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(SearchController.class)
public class SearchControllerIT {

    private static final SearchResult COCKTAIL_RESULT = new SearchResult()
            .name("bla").description("foo").id(UUID.randomUUID()).type(SearchResult.TypeEnum.COCKTAIL);

    private static final SearchResult INGREDIENT_RESULT = new SearchResult()
            .name("bar").description("baz").id(UUID.randomUUID()).type(SearchResult.TypeEnum.COCKTAIL);

    @MockBean
    private SearchService searchService;

    @MockBean
    private UserInterceptor interceptor;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        when(interceptor.preHandle(any(), any(), any())).thenReturn(true);
    }

    @Test
    public void testSearchNoTerm() throws Exception {
        mockMvc.perform(get("/api/search"))
                .andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @MethodSource("results")
    public void testSearch(List<SearchResult> results) throws Exception {
        when(searchService.search(any(), any())).thenReturn(results);

        mockMvc.perform(get("/api/search")
                .queryParam("q", "term"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(results.size())));
    }

    private static Stream<Arguments> results() {
        return Stream.of(
                Arguments.of(List.of()),
                Arguments.of(List.of(COCKTAIL_RESULT)),
                Arguments.of(List.of(INGREDIENT_RESULT)),
                Arguments.of(List.of(INGREDIENT_RESULT, COCKTAIL_RESULT))
        );
    }

}
