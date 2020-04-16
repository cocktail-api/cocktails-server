SELECT *
FROM language
WHERE language.language = :languge
  AND language.country = :country;
