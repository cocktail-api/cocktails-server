ALTER TABLE ingredient
    ADD COLUMN ingredient_type_uuid UUID,
    DROP CONSTRAINT ingredient_owner_check,
    DROP CONSTRAINT ingredient_owner_fkey,
    DROP COLUMN owner,
    ADD COLUMN owner                UUID,
    ADD CONSTRAINT ingredient_owner_check CHECK ( owner IS NOT NULL OR public = TRUE),
    DROP CONSTRAINT ingredient_uuid_key;

UPDATE ingredient
SET ingredient_type_uuid = (
    SELECT ingredient_type.uuid
    FROM ingredient_type
    WHERE ingredient_type.id = ingredient.ingredient_type_id);

ALTER TABLE ingredient
    DROP CONSTRAINT ingredient_ingredient_type_id_fkey,
    DROP COLUMN ingredient_type_id,
    DROP CONSTRAINT ingredient_pkey,
    ADD PRIMARY KEY (uuid),
    DROP COLUMN id;

ALTER TABLE ingredient_type
    DROP CONSTRAINT ingredient_type_uuid_key,
    DROP CONSTRAINT ingredient_type_pkey,
    ADD PRIMARY KEY (uuid),
    DROP COLUMN id;

ALTER TABLE "user"
    DROP CONSTRAINT user_uuid_key,
    DROP CONSTRAINT user_pkey,
    ADD PRIMARY KEY (uuid),
    DROP COLUMN id;

ALTER TABLE ingredient
    ADD CONSTRAINT ingredient_ingredient_type_fkey
        FOREIGN KEY (ingredient_type_uuid) REFERENCES ingredient_type (uuid),
    ADD CONSTRAINT ingredient_user_fkey
        FOREIGN KEY (owner) REFERENCES "user" (uuid);
