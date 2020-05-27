package de.slevermann.cocktails.model.db;

import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
public class DbCreateIngredient {

    UUID typeId;

    Map<String, String> names;

    Map<String, String> descriptions;
}