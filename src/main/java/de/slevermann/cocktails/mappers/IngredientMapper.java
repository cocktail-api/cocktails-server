package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.HStoreColumnMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientMapper implements RowMapper<Ingredient> {

    private final HStoreColumnMapper hStoreColumnMapper;

    public IngredientMapper(HStoreColumnMapper hStoreColumnMapper) {
        this.hStoreColumnMapper = hStoreColumnMapper;
    }

    @Override
    public Ingredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        TranslatedString typeName = new TranslatedString();
        typeName.putAll(hStoreColumnMapper.map(rs, "type_name", ctx));
        TranslatedString name = new TranslatedString();
        name.putAll(hStoreColumnMapper.map(rs, "name", ctx));
        TranslatedString description = new TranslatedString();
        description.putAll(hStoreColumnMapper.map(rs, "description", ctx));
        return new Ingredient()
                .id(rs.getLong("id"))
                .type(new IngredientType()
                        .id(rs.getLong("type_id"))
                        .names(typeName))
                .names(name)
                .descriptions(description);
    }
}
