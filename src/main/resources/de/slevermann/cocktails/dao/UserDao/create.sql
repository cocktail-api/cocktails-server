INSERT INTO "user" (provider_id)
VALUES (:providerId)
RETURNING uuid, provider_id, nick
