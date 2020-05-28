package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.TranslatedString;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TranslatedStringMapper {

    public Map<String, String> translatedStringsToMap(List<TranslatedString> translatedStrings) {
        Map<String, String> map = new HashMap<>();

        for (TranslatedString string : translatedStrings) {
            map.put(string.getLanguage(), string.getTranslation());
        }
        return map;
    }

    public List<TranslatedString> mapToTranslatedStrings(Map<String, String> translatedStrings) {
        return translatedStrings.entrySet().stream().map(e -> new TranslatedString()
                .language(e.getKey())
                .translation(e.getValue())).collect(Collectors.toList());
    }
}
