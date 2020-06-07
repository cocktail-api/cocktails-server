CREATE TYPE MODERATION AS ENUM ('WAITING', 'REJECTED');
-- If moderation is not null, then an ingredient must not be public,
-- and it must have an owner
ALTER TABLE ingredient
    ADD COLUMN moderation MODERATION,
    ADD CONSTRAINT moderation_check CHECK (
        NOT ((owner is NULL or published = TRUE) AND moderation IS NOT NULL));
