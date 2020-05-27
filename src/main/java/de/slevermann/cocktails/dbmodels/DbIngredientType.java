package de.slevermann.cocktails.dbmodels;

import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class DbIngredientType {

    private UUID uuid;

    private Map<String, String> names;

    public IngredientType toIngredientType() {
        return new IngredientType()
                .id(uuid)
                .names(names.entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()));
    }
}