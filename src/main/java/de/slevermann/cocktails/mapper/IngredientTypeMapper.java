package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.IngredientType;
import de.slevermann.cocktails.model.TranslatedString;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class IngredientTypeMapper {

    public IngredientType dbIngredientTypetoIngredientType(DbIngredientType dbIngredientType) {
        return new IngredientType()
                .id(dbIngredientType.getUuid())
                .names(dbIngredientType.getNames().entrySet().stream()
                        .map(e -> new TranslatedString()
                                .language(e.getKey())
                                .translation(e.getValue()))
                        .collect(Collectors.toList()));
    }

}
