package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.model.db.DbCreateIngredient;
import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.LocalizedIngredient;
import de.slevermann.cocktails.model.SearchResult;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;
import java.util.UUID;

@UseClasspathSqlLocator
public interface IngredientDao {

    @SqlQuery
    List<LocalizedIngredient> getAll(@Bind("preferred_locale") String preferredLocale);

    @SqlQuery
    DbIngredient getById(@Bind("uuid") UUID uuid);

    @SqlQuery
    List<SearchResult> search(@Bind("query") String query, @Bind("preferred_locale") String preferredLocale);

    @SqlUpdate
    @GetGeneratedKeys
    DbIngredient create(@BindBean DbCreateIngredient dbCreateIngredient);

    @SqlUpdate
    @GetGeneratedKeys
    DbIngredient update(@Bind("uuid") UUID id, @BindBean DbCreateIngredient ingredient);

    @SqlUpdate
    int delete(@Bind("uuid") UUID id);
}