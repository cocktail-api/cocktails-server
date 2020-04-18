SELECT id,
       find_language(name, :preferred_locale)     AS language,
       find_translations(name, :preferred_locale) AS type
FROM ingredient_type;
