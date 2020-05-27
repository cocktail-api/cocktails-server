package de.slevermann.cocktails.dbmodels;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.TranslatedString;
import lombok.Builder;
import lombok.Data;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
public class DbIngredient {

    private UUID uuid;

    private DbIngredientType type;

    private Map<String, String> names;

    private Map<String, String> descriptions;

    public Ingredient toIngredient() {
        return new Ingredient()
                .id(uuid)
                .type(type.toIngredientType())
                .names(names.entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()))
                .descriptions(descriptions.entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()));
    }
}
