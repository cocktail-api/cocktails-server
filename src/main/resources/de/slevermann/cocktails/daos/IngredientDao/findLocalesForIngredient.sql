SELECT locale
FROM ingredient
         JOIN ingredient_data idata on ingredient.id = idata.ingredient_id
         JOIN language l on idata.language_id = l.id
WHERE ingredient.id = :id;
