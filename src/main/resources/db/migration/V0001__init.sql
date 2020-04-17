CREATE EXTENSION hstore;
CREATE EXTENSION pg_trgm;

CREATE TABLE ingredient_type
(
    id   BIGSERIAL PRIMARY KEY,
    name HSTORE NOT NULL
);

CREATE TABLE ingredient
(
    id                 BIGSERIAL PRIMARY KEY,
    ingredient_type_id BIGINT NOT NULL REFERENCES ingredient_type (id),
    name               HSTORE NOT NULL,
    description        HSTORE NOT NULL
);
ALTER TABLE ingredient
    ADD CONSTRAINT ingredient_consistency_check CHECK (
        akeys(name) = akeys(description)
        ),
    ADD CONSTRAINT ingredient_non_empty_check CHECK (
        name <> '' AND description <> ''
        );

INSERT INTO ingredient_type (id, name)
VALUES (1, '"en" => "Hard Liquor","de" => "Starker Alkohol"'),
       (2, '"en" => "Juice","de" => "Fruchtsaft"'),
       (3, '"en" => "Syrup","de" => "Sirup"'),
       (4, '"en" => "Ice","de" => "Eis"');
ALTER SEQUENCE ingredient_type_id_seq RESTART WITH 5;
INSERT INTO ingredient (id, ingredient_type_id, name, description)
VALUES (1, 1, '"en" => "White rum", "de" => "Weißer Rum"',
        '"en" => "A high proof spirit made from sugar cane", "de" => "Ein hochprozentiges Destillat aus Zuckerrohr"'),
       (2, 2, '"en" => "Lime juice", "de" => "Limettensaft"',
        '"en" => "A very sour citrus juice", "de" => "Ein sehr saurer Zitrussaft"'),
       (3, 3, '"en" => "Simple syrup", "de" => "Zuckersirup"',
        '"en" => "A syrup made from water and sugar with a ratio of 1:1 by weight", "de" => "Ein Sirup aus Zucker und Wasser im Gewichtsverhältnis 1:1"'),
       (4, 4, '"en" => "Ice cubes", "de" => "Eiswürfel"',
        '"en" => "Ice in cube shape", "de" => "Eis in Würfelform"');
ALTER SEQUENCE ingredient_id_seq RESTART WITH 5;
