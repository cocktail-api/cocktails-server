package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.models.LocalizedIngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class LocalizedIngredientTypeRowMapper implements RowMapper<LocalizedIngredientType> {
    @Override
    public LocalizedIngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LocalizedIngredientType()
                .id(UUID.fromString(rs.getString("uuid")))
                .language(rs.getString("language"))
                .type(rs.getString("type"));
    }
}
