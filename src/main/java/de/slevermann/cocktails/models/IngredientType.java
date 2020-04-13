package de.slevermann.cocktails.models;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Gets or Sets IngredientType
 */
public enum IngredientType {
  HARD_LIQUOR("HARD_LIQUOR"),
    LIQUOR("LIQUOR"),
    SOFT_DRINK("SOFT_DRINK"),
    JUICE("JUICE"),
    FRUIT("FRUIT"),
    OTHER("OTHER");

  private String value;

  IngredientType(String value) {
    this.value = value;
  }

  @Override
  @JsonValue
  public String toString() {
    return String.valueOf(value);
  }

  @JsonCreator
  public static IngredientType fromValue(String text) {
    for (IngredientType b : IngredientType.values()) {
      if (String.valueOf(b.value).equals(text)) {
        return b;
      }
    }
    return null;
  }
}
