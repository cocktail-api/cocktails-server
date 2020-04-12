package de.slevermann.cocktails.daos;

import de.slevermann.cocktails.models.Ingredient;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlBatch;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@UseClasspathSqlLocator
public interface IngredientDao {

    @SqlQuery
    Ingredient findById(Long id);

    @SqlQuery
    Ingredient findByNameAndLocale(String name, String locale);

    @SqlUpdate
    void deleteById(Long id);

    @SqlBatch
    void batchDelete(@Bind("id") List<Long> ids);

    @SqlUpdate
    @GetGeneratedKeys
    Long insert(@BindBean Ingredient ingredient);

    @SqlBatch
    @GetGeneratedKeys
    List<Long> batchInsert(@BindBean List<Ingredient> ingredients);

    @SqlQuery
    Long count();
}
