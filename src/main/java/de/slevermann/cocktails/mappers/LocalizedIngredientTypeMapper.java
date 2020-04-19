package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.LocalizedIngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class LocalizedIngredientTypeMapper implements RowMapper<LocalizedIngredientType> {
    @Override
    public LocalizedIngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LocalizedIngredientType()
                .id(rs.getLong("id"))
                .language(rs.getString("language"))
                .type(rs.getString("type"));
    }
}