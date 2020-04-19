SELECT i.id          AS id,
       it.id         AS type_id,
       it.name       AS type_name,
       i.name        AS name,
       i.description AS description
FROM ingredient i
         JOIN ingredient_type it on i.ingredient_type_id = it.id
WHERE i.id = :id
