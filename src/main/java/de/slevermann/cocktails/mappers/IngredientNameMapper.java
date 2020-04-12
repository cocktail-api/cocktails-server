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
        IngredientName name = new IngredientName();
        name.setId(rs.getLong("id"));
        name.setName(rs.getString("name"));
        name.setLocale(rs.getString("locale"));
        return name;
    }
}
