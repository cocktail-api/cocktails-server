UPDATE ingredient
SET ingredient_type_uuid = :typeId,
    name                 = name || :names,
    description          = description || :descriptions
WHERE ingredient.uuid = :uuid
RETURNING uuid AS uuid,
    ingredient_type_uuid AS type_uuid,
    (SELECT name FROM ingredient_type WHERE ingredient_type.uuid = ingredient_type_uuid) AS type_name,
    name as name,
    description as description,
    public as public,
    owner as owner;
