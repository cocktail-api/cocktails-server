package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.IngredientTypeName;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientTypeNameMapper implements RowMapper<IngredientTypeName> {
    @Override
    public IngredientTypeName map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new IngredientTypeName()
                .name(rs.getString("name"))
                .language(rs.getString("language_language"))
                .country(rs.getString("language_country"));
    }
}
