SELECT l.*
FROM ingredient
         JOIN ingredient_data idata ON ingredient.id = idata.ingredient_id
         JOIN language l ON idata.language_language = l.language AND idata.language_country = l.country
WHERE ingredient.id = :id;
