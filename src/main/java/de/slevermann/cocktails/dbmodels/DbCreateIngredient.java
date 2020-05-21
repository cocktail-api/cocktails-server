package de.slevermann.cocktails.dbmodels;

import de.slevermann.cocktails.models.CreateIngredient;
import de.slevermann.cocktails.models.TranslatedString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class DbCreateIngredient {

    private Long typeId;

    private Map<String, String> names;

    private Map<String, String> descriptions;

    public DbCreateIngredient(CreateIngredient ingredient) {
        typeId = ingredient.getTypeId();
        names = new HashMap<>();

        for (TranslatedString name : ingredient.getNames()) {
            names.put(name.getLanguage(), name.getTranslation());
        }

        descriptions = new HashMap<>();
        for (TranslatedString description : ingredient.getDescriptions()) {
            descriptions.put(description.getLanguage(), description.getTranslation());
        }
    }
}
