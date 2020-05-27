package de.slevermann.cocktails.dbmodels;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbIngredient {

    UUID uuid;

    DbIngredientType type;

    Map<String, String> names;

    Map<String, String> descriptions;

    boolean isPublic;

    DbUserInfo userInfo;
}
