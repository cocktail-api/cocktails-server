SELECT i.id                                                              AS id,
       it.id                                                             AS type_id,
       find_language(it.name, :preferred_locale)           AS type_language,
       find_translations(it.name, :preferred_locale)       AS type_name,
       find_language(i.name, :preferred_locale)            AS language,
       find_translations(i.name, :preferred_locale)        AS name,
       find_translations(i.description, :preferred_locale) AS description
FROM ingredient i
         JOIN ingredient_type it on i.ingredient_type_id = it.id
