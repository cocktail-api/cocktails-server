package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.TranslatedString;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TranslatedStringMapperTest {

    private final TranslatedStringMapper translatedStringMapper = new TranslatedStringMapper();

    @Test
    public void testEmptyMap() {
        List<TranslatedString> translatedStrings = translatedStringMapper.mapToTranslatedStrings(Collections.emptyMap());

        assertTrue(translatedStrings.isEmpty(), "Empty map should yield empty list");
    }

    @Test
    public void testEmptyList() {
        Map<String, String> translatedStrings = translatedStringMapper.translatedStringsToMap(Collections.emptyList());

        assertTrue(translatedStrings.isEmpty(), "Empty list should yield empty map");
    }

    @Test
    public void testMapToList() {
        TranslatedString one = new TranslatedString().language("de").translation("hallo");
        TranslatedString two = new TranslatedString().language("en").translation("hello");

        Map<String, String> translatedStrings = translatedStringMapper.translatedStringsToMap(List.of(one, two));

        assertEquals(2, translatedStrings.size(), "Exactly 2 entries should be present");
        assertEquals("hallo", translatedStrings.get("de"), "Translations should be mapped correctly");
        assertEquals("hello", translatedStrings.get("en"), "Translations should be mapped correctly");
    }

    @Test
    public void testListToMap() {
        List<TranslatedString> translatedStrings = translatedStringMapper.mapToTranslatedStrings(
                Map.of("de", "hallo", "en", "hello"));

        assertEquals(2, translatedStrings.size(), "Exactly 2 entries should be present");
        for (TranslatedString translatedString : translatedStrings) {
            if (translatedString.getLanguage().equals("de")) {
                assertEquals("hallo", translatedString.getTranslation(), "Translations should be mapped correctly");
            } else if (translatedString.getLanguage().equals("en")) {
                assertEquals("hello", translatedString.getTranslation(), "Translations should be mapped correctly");
            } else {
                fail("Unexpected language in result");
            }
        }
    }
}
