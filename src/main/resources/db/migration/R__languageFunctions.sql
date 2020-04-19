-- When support for new languages is added, this file needs to be updated to reflect the newly added language

-- Find the best suitable language for an entry
-- Returns the preferred input if it is present as a key, otherwise falls back through the list to different values
CREATE OR REPLACE FUNCTION find_language(store hstore, preferred varchar) RETURNS varchar AS
$$
SELECT CASE
           WHEN store ? preferred THEN preferred
           WHEN store ? 'en' THEN 'en'
           WHEN store ? 'de' THEN 'de'
           END
$$
    LANGUAGE SQL STABLE
                 STRICT;

-- Find the actual translations for an entry
-- Returns the preferred language if it is present as a key, otherwise falls back through the list to different values
CREATE OR REPLACE FUNCTION find_translations(store hstore, preferred varchar) RETURNS varchar AS
$$
SELECT COALESCE(store -> preferred,
                store -> 'en',
                store -> 'de')
$$
    LANGUAGE SQL STABLE
                 STRICT;

-- Search for a search term in a trigram indexed column
-- This should be used for "exact" for substring matching  '%multiple searchExact words%'
CREATE OR REPLACE FUNCTION match_substring(store hstore, query text) RETURNS BOOLEAN AS
$$
SELECT store -> 'en' ILIKE query
           OR store -> 'de' ILIKE query
$$
    LANGUAGE SQL
    STABLE;

-- Create trigram indexes for searching
CREATE INDEX IF NOT EXISTS ingredient_name_index_en
    ON ingredient USING GIN ((name -> 'en') gin_trgm_ops);
CREATE INDEX IF NOT EXISTS ingredient_name_index_de
    ON ingredient USING GIN ((name -> 'de') gin_trgm_ops);

CREATE INDEX IF NOT EXISTS ingredient_description_index_en
    ON ingredient USING GIN ((description -> 'en') gin_trgm_ops);
CREATE INDEX IF NOT EXISTS ingredient_description_index_de
    ON ingredient USING GIN ((description -> 'de') gin_trgm_ops);

-- Do we really need these?
CREATE INDEX IF NOT EXISTS ingredient_type_name_index_en
    ON ingredient_type USING GIN ((name -> 'en') gin_trgm_ops);
CREATE INDEX IF NOT EXISTS ingredient_type_name_index_de
    ON ingredient_type USING GIN ((name -> 'de') gin_trgm_ops);
