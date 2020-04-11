package de.slevermann.cocktails.models;

import org.springframework.context.support.MessageSourceAccessor;

import java.util.Locale;

public enum IngredientType {
    HARD_LIQUOR,
    LIQUOR,
    SOFT_DRINK,
    JUICE,
    FRUIT,
    OTHER;

    public String getLocalisedName(Locale locale, MessageSourceAccessor messageSourceAccessor) {
        switch (this) {
            case HARD_LIQUOR:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.hard_liquor", locale);
            case LIQUOR:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.liquor", locale);
            case SOFT_DRINK:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.soft_drink", locale);
            case JUICE:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.juice", locale);
            case FRUIT:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.fruit", locale);
            case OTHER:
                return messageSourceAccessor.getMessage("de.slevermann.cocktails.ingredients.types.other", locale);
            default:
                throw new RuntimeException("Missing translation data for " + this.toString() + ". This is a bug.");
        }
    }
}
