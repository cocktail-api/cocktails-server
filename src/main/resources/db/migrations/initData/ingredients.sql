INSERT INTO ingredient_type (id)
VALUES (1),
       (2),
       (3),
       (4);
ALTER SEQUENCE ingredient_type_id_seq RESTART WITH 5;

INSERT INTO ingredient_type_name (ingredient_type_id, language_id, name)
VALUES (1, 1, 'Starker Alkohol'),
       (1, 2, 'Hard Liquor'),
       (2, 1, 'Fruchtsaft'),
       (2, 2, 'Juice'),
       (3, 1, 'Sirup'),
       (3, 2, 'Syrup'),
       (4, 1, 'Eis'),
       (4, 2, 'Ice');

INSERT INTO ingredient (id, ingredient_type_id)
VALUES (1, 1),
       (2, 2),
       (3, 3),
       (4, 4);
ALTER SEQUENCE ingredient_id_seq RESTART WITH 5;

INSERT INTO ingredient_data (ingredient_id, language_id, name, description)
VALUES (1, 1, 'Weißer Rum', 'Ein hochprozentiges Destillat aus Zuckerrohr'),
       (1, 2, 'White rum', 'A high proof spirit made from sugar cane'),
       (2, 1, 'Limettensaft', 'Ein sehr saurer Zitrussaft'),
       (2, 2, 'Lime juice', 'A very sour citrus juice'),
       (3, 1, 'Zuckersirup', 'Ein Sirup aus Zucker und Wasser im Gewichtsverhältnis 1:1'),
       (3, 2, 'Simple syrup', 'A syrup made from water and sugar with a ratio of 1:1 by weight'),
       (4, 1, 'Eiswürfel', 'Eis in Würfelform'),
       (4, 2, 'Ice cubes', 'Ice in cube shape');
