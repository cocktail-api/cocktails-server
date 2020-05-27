SELECT uuid                                              AS id,
       find_translations(name, :preferred_locale)        AS name,
       find_translations(description, :preferred_locale) AS description,
       'INGREDIENT'                                      AS type
FROM ingredient
WHERE match_substring(name, :query)
   OR match_substring(description, :query);
