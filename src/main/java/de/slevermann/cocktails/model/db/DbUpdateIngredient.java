package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Builder
@Value
public class DbUpdateIngredient {
    @NonNull UUID typeId;

    @NonNull Map<String, String> names;

    @NonNull Map<String, String> descriptions;
}
