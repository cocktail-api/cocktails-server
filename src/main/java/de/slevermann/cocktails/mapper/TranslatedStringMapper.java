package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.TranslatedString;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TranslatedStringMapper {

    public Map<String, String> translatedStringsToHashMap(List<TranslatedString> translatedStrings) {
        Map<String, String> map = new HashMap<>();

        for (TranslatedString string : translatedStrings) {
            map.put(string.getLanguage(), string.getTranslation());
        }
        return map;
    }
}
