package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbIngredientType {

    @NonNull UUID uuid;

    @NonNull Map<String, String> names;
}
