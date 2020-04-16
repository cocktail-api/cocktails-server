UPDATE language
SET country        = 'DE',
    name           = 'German (Germany)',
    localized_name = 'Deutsch (Deutschland)'
WHERE language.locale LIKE 'de';
UPDATE language
SET country        = 'US',
    name           = 'English (United States)',
    localized_name = 'English (United States)'
WHERE language.locale LIKE 'en';
