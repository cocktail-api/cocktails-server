package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientMapper implements RowMapper<Ingredient> {
    @Override
    public Ingredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        Ingredient ingredient = new Ingredient();
        ingredient.setId(rs.getLong("id"));
        return ingredient;
    }
}
