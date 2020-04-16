UPDATE ingredient_type_name
SET language_language = (SELECT language.language
                         FROM language
                         WHERE language.id = ingredient_type_name.language_id),
    language_country  = (SELECT language.country
                         FROM language
                         WHERE language.id = ingredient_type_name.language_id);
UPDATE ingredient_data
SET language_language = (SELECT language.language
                         FROM language
                         WHERE language.id = ingredient_data.language_id),
    language_country  = (SELECT language.country
                         FROM language
                         WHERE language.id = ingredient_data.language_id);
