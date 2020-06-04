package de.slevermann.cocktails.exception;

import java.util.UUID;

public class MissingIngredientTypeException extends RuntimeException {
    public MissingIngredientTypeException(UUID uuid) {
        super("Ingredient Type with ID " + uuid + " does not exist");
    }
}
