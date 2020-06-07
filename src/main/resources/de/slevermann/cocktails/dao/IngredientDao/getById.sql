SELECT i.uuid        AS uuid,
       it.uuid       AS type_uuid,
       it.name       AS type_name,
       i.name        AS name,
       i.description AS description,
       i.published   AS published,
       u.uuid        AS owner_uuid,
       u.nick        AS owner_nick,
       u.provider_id AS owner_provider_id,
       i.moderation  AS moderation
FROM ingredient i
         JOIN ingredient_type it on i.ingredient_type_uuid = it.uuid
         LEFT JOIN "user" u on i.owner = u.uuid
WHERE i.uuid = :uuid
