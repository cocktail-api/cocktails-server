package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.mappers.IngredientTypeRowReducer;
import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.UseRowReducer;

import java.util.List;

@UseClasspathSqlLocator
public interface IngredientTypeDao {

    @SqlQuery
    @UseRowReducer(IngredientTypeRowReducer.class)
    List<IngredientType> findAll();
}
