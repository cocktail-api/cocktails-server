SELECT uuid, provider_id, nick
FROM "user"
WHERE provider_id = :providerId
