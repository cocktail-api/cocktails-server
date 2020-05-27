package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbIngredientType {

    UUID uuid;

    Map<String, String> names;
}
