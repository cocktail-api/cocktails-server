package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.LocalizedIngredientType;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;
import java.util.UUID;

@UseClasspathSqlLocator
public interface IngredientTypeDao {

    @SqlQuery
    List<LocalizedIngredientType> getAll(@Bind("preferred_locale") String preferredLocale);

    @SqlQuery
    DbIngredientType getById(@Bind("uuid") UUID uuid);
}
