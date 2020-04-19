UPDATE ingredient
SET ingredient_type_id = :typeId,
    name               = name || :names,
    description        = description || :descriptions
WHERE id = :id
