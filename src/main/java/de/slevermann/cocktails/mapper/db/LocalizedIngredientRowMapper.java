package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.model.LocalizedIngredient;
import de.slevermann.cocktails.model.LocalizedIngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class LocalizedIngredientRowMapper implements RowMapper<LocalizedIngredient> {
    @Override
    public LocalizedIngredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LocalizedIngredient()
                .id(UUID.fromString(rs.getString("id")))
                .type(new LocalizedIngredientType()
                        .id(UUID.fromString(rs.getString("type_id")))
                        .language(rs.getString("type_language"))
                        .type(rs.getString("type_name")))
                .language(rs.getString("language"))
                .name(rs.getString("name"))
                .description(rs.getString("description"));
    }
}
