package de.slevermann.cocktails.config;

import org.springframework.context.annotation.Configuration;

import java.util.Set;

/**
 * This class denotes the languages supported by the application.
 * <p>
 * Before adding a language to this file, the language needs to be added to the
 * SQL functions in src/main/resources/db/migration/R__languageFunctions.sql.
 * <p>
 * If this is not done, the new language will not be found in any search queries.
 */
@Configuration
public class LanguageConfiguration {

    public Set<String> getSupportedLanguages() {
        return Set.of("de", "en");
    }
}
