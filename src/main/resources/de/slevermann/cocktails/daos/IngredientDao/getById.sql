SELECT i.uuid        AS uuid,
       it.uuid       AS type_uuid,
       it.name       AS type_name,
       i.name        AS name,
       i.description AS description
FROM ingredient i
         JOIN ingredient_type it on i.ingredient_type_uuid = it.uuid
WHERE i.uuid = :uuid
