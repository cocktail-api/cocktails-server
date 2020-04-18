package de.slevermann.cocktails.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientMapper implements RowMapper<Ingredient> {

    private final Jackson2ObjectMapperBuilder builder;

    public IngredientMapper(Jackson2ObjectMapperBuilder builder) {
        this.builder = builder;
    }

    @Override
    public Ingredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        ObjectMapper om = builder.build();

        TranslatedString typeName, name, description;
        try {
            typeName = om.readValue(rs.getString("type_name"), TranslatedString.class);
            name = om.readValue(rs.getString("name"), TranslatedString.class);
            description = om.readValue(rs.getString("description"), TranslatedString.class);
        } catch (JsonProcessingException e) {
            throw new SQLException(e);
        }
        return new Ingredient()
                .id(rs.getLong("id"))
                .type(new IngredientType()
                        .id(rs.getLong("type_id"))
                        .names(typeName))
                .names(name)
                .descriptions(description);
    }
}
