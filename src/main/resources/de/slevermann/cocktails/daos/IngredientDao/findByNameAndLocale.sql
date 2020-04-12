SELECT ingredient.*
FROM ingredient
         JOIN ingredient_name
              ON ingredient_name.ingredient_id = ingredient.id
                  AND ingredient_name.name = :name
                  AND ingredient_name.locale = :locale
