CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE "user"
(
    id          BIGSERIAL PRIMARY KEY,
    uuid        UUID UNIQUE NOT NULL DEFAULT uuid_generate_v4(),
    provider_id VARCHAR     NOT NULL UNIQUE,
    nick        VARCHAR UNIQUE
);

ALTER TABLE ingredient
    ADD COLUMN owner  BIGINT REFERENCES "user" (id),
    ADD COLUMN public BOOLEAN     NOT NULL DEFAULT FALSE,
    ADD COLUMN uuid   UUID UNIQUE NOT NULL DEFAULT uuid_generate_v4();

UPDATE ingredient
SET public = TRUE
WHERE owner IS NULL;

ALTER TABLE ingredient
    ADD CONSTRAINT ingredient_owner_check CHECK ( owner IS NOT NULL OR public = TRUE );

ALTER TABLE ingredient_type
    ADD COLUMN uuid UUID UNIQUE NOT NULL DEFAULT uuid_generate_v4();
