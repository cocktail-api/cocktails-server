package de.slevermann.cocktails.models;

import lombok.Data;

import java.util.List;

@Data
public class IngredientTag {

    private Long id;

    private String tag;

    private List<Ingredient> ingredients;
}
