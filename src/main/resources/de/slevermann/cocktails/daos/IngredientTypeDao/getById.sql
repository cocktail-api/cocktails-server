SELECT id,
       name
FROM ingredient_type
WHERE id = :id;

SELECT similarity('Zuckersirup', 'Zu');
SELECT 'Zuckersirup' % 'Zu';
SELECT 'Zu' % 'Zuckersirup';
