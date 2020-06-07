INSERT INTO ingredient (ingredient_type_uuid, name, description, published, owner)
VALUES (:typeId, :names, :descriptions, :published, :owner)
RETURNING uuid AS uuid,
    ingredient_type_uuid AS type_uuid,
    (SELECT name FROM ingredient_type WHERE ingredient_type.uuid = ingredient_type_uuid) AS type_name,
    name as name,
    description as description,
    published as published,
    owner as owner_uuid,
    (SELECT nick FROM "user" WHERE "user".uuid = owner) AS owner_nick,
    (SELECT provider_id FROM "user" WHERE "user".uuid = owner) AS owner_provider_id,
    moderation as moderation;
