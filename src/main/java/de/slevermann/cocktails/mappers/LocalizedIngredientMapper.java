package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.LocalizedIngredient;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class LocalizedIngredientMapper implements RowMapper<LocalizedIngredient> {
    @Override
    public LocalizedIngredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LocalizedIngredient()
                .id(rs.getLong("id"))
                .typeId(rs.getLong("type_id"))
                .typeLanguage(rs.getString("type_language"))
                .type(rs.getString("type_name"))
                .language(rs.getString("language"))
                .name(rs.getString("name"))
                .description(rs.getString("description"));
    }
}
