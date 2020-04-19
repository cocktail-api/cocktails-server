package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.CreateIngredient;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.LocalizedIngredient;
import de.slevermann.cocktails.models.SearchResult;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface IngredientDao {

    @SqlQuery
    List<LocalizedIngredient> getAll(@Bind("preferred_locale") String preferredLocale);

    @SqlQuery
    Ingredient getById(@Bind("id") Long id);

    @SqlQuery
    List<SearchResult> search(@Bind("query") String query, @Bind("preferred_locale") String preferredLocale);

    @SqlUpdate
    @GetGeneratedKeys
    Long create(@BindBean CreateIngredient createIngredient);

    @SqlUpdate
    long update(@Bind("id") Long id, @BindBean CreateIngredient ingredient);
}
