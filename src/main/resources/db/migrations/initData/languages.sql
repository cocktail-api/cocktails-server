INSERT INTO language (id, locale, name, localized_name)
VALUES (1, 'de', 'German', 'Deutsch'),
       (2, 'en', 'English', 'English');

ALTER SEQUENCE language_id_seq RESTART WITH 3;
