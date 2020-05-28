package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.IngredientType;
import org.springframework.stereotype.Component;

@Component
public class IngredientTypeMapper {

    private final TranslatedStringMapper translatedStringMapper;

    public IngredientTypeMapper(TranslatedStringMapper translatedStringMapper) {
        this.translatedStringMapper = translatedStringMapper;
    }

    public IngredientType dbIngredientTypetoIngredientType(DbIngredientType dbIngredientType) {
        return new IngredientType()
                .id(dbIngredientType.getUuid())
                .names(translatedStringMapper.mapToTranslatedStrings(dbIngredientType.getNames()));
    }

}
