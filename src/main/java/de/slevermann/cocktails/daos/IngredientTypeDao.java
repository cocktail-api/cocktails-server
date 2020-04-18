package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.LocalizedIngredientType;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

@UseClasspathSqlLocator
public interface IngredientTypeDao {

    @SqlQuery
    List<LocalizedIngredientType> getAll(@Bind("preferred_locale") String preferredLocale);

    @SqlQuery
    IngredientType getById(@Bind("id") Long id);
}
