UPDATE ingredient
SET published  = TRUE,
    moderation = NULL
WHERE uuid = :uuid;
