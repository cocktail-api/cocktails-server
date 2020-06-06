package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbCreateIngredient {

    @NonNull UUID typeId;

    @NonNull Map<String, String> names;

    @NonNull Map<String, String> descriptions;

    boolean published;

    UUID owner;
}
