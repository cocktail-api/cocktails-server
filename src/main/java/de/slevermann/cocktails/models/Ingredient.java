package de.slevermann.cocktails.models;

import lombok.Data;

import java.util.List;

@Data
public class Ingredient {

    private Long id;

    private List<IngredientName> names;

    private IngredientType type;

    private List<IngredientTag> tags;

    private List<IngredientDescription> descriptions;
}
