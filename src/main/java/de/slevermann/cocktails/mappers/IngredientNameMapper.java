package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.IngredientName;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientNameMapper implements RowMapper<IngredientName> {

    @Override
    public IngredientName map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new IngredientName().id(rs.getLong("id"));
    }
}
