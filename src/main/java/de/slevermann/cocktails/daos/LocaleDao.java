package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.CreateLocale;
import de.slevermann.cocktails.models.Locale;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface LocaleDao {

    @SqlQuery
    List<Locale> getAll();

    @SqlQuery
    Locale findByTuple(@Bind String language, @Bind String country);
}
