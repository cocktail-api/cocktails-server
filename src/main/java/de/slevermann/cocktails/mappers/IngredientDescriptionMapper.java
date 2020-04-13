package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.IngredientDescription;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientDescriptionMapper implements RowMapper<IngredientDescription> {
    @Override
    public IngredientDescription map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new IngredientDescription().id(rs.getLong("id"));
    }
}
