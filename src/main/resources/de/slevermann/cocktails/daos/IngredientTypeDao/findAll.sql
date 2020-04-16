SELECT *
FROM ingredient_type
         JOIN ingredient_type_name itn on ingredient_type.id = itn.ingredient_type_id;
