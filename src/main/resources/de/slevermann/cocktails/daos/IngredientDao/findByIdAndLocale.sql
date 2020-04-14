SELECT id.description,
       id.name                AS name,
       itn.name               AS typename,
       itn.ingredient_type_id AS typeid,
       l.locale,
       ingredient.id          AS id
FROM ingredient
         JOIN ingredient_data id ON ingredient.id = id.ingredient_id
         JOIN ingredient_type_name itn
              ON id.language_id = itn.language_id AND itn.ingredient_type_id = ingredient.ingredient_type_id
         JOIN language l on id.language_id = l.id
WHERE ingredient.id = :id
  AND l.locale LIKE :locale;
