SELECT id,
       hstore_to_json(name) AS name
FROM ingredient_type
WHERE id = :id;
