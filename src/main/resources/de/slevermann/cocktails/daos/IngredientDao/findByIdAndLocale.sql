SELECT id.description         AS description,
       id.name                AS name,
       itn.name               AS typename,
       itn.ingredient_type_id AS typeid,
       l.language             AS language,
       l.country              AS country,
       ingredient.id          AS id
FROM ingredient
         JOIN ingredient_data id ON ingredient.id = id.ingredient_id
         JOIN language l ON id.language_language = l.language AND id.language_country = l.country
         JOIN ingredient_type_name itn
              ON id.language_language = itn.language_language AND id.language_country = itn.language_country AND
                 itn.ingredient_type_id = ingredient.ingredient_type_id
WHERE ingredient.id = :id
  AND l.language LIKE :language
  AND l.country LIKE :country;
