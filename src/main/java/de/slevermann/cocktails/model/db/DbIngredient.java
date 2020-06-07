package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbIngredient {

    @NonNull UUID uuid;

    @NonNull DbIngredientType type;

    @NonNull Map<String, String> names;

    @NonNull Map<String, String> descriptions;

    boolean published;

    DbUserInfo userInfo;

    DbModeration moderation;
}
