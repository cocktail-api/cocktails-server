package de.slevermann.cocktails.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class IngredientDescription {

    private Long id;

    private String locale;

    private String description;

    @EqualsAndHashCode.Exclude
    @JsonManagedReference
    @ToString.Exclude
    private Ingredient ingredient;
}
