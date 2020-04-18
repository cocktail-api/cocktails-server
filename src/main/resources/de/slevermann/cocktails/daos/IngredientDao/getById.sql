SELECT i.id                          AS id,
       it.id                         AS type_id,
       hstore_to_json(it.name)       AS type_name,
       hstore_to_json(i.name)        AS name,
       hstore_to_json(i.description) AS description
FROM ingredient i
         JOIN ingredient_type it on i.ingredient_type_id = it.id
WHERE i.id = :id
