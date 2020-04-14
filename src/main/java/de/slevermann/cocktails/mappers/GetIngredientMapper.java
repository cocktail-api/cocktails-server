package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.GetIngredient;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class GetIngredientMapper implements RowMapper<GetIngredient> {
    @Override
    public GetIngredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new GetIngredient()
                .id(rs.getLong("id"))
                .locale(rs.getString("locale"))
                .typeId(rs.getLong("typeid"))
                .type(rs.getString("typename"))
                .name(rs.getString("name"))
                .description(rs.getString("description"));
    }
}
