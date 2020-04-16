package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.IngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientTypeMapper implements RowMapper<IngredientType> {
    @Override
    public IngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new IngredientType().id(rs.getLong("id"));
    }
}
