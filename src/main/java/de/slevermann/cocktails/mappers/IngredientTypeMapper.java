package de.slevermann.cocktails.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientTypeMapper implements RowMapper<IngredientType> {

    private final Jackson2ObjectMapperBuilder builder;

    public IngredientTypeMapper(Jackson2ObjectMapperBuilder builder) {
        this.builder = builder;
    }

    @Override
    public IngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        TranslatedString translatedString;
        try {
            translatedString = builder.build().readValue(rs.getString("name"), TranslatedString.class);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }

        return new IngredientType()
                .id(rs.getLong("id"))
                .names(translatedString);
    }
}
