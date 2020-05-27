package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.dbmodels.DbIngredient;
import de.slevermann.cocktails.dbmodels.DbIngredientType;
import de.slevermann.cocktails.models.Ingredient;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.HStoreColumnMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mapper
public class IngredientMapper implements RowMapper<DbIngredient> {

    private final HStoreColumnMapper hStoreColumnMapper;

    public IngredientMapper(HStoreColumnMapper hStoreColumnMapper) {
        this.hStoreColumnMapper = hStoreColumnMapper;
    }

    @Override
    public DbIngredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        return DbIngredient.builder()
                .uuid(UUID.fromString(rs.getString("uuid")))
                .type(DbIngredientType.builder()
                        .uuid(UUID.fromString(rs.getString("type_uuid")))
                        .names(hStoreColumnMapper.map(rs, "type_name", ctx)).build())
                .names(hStoreColumnMapper.map(rs, "name", ctx))
                .descriptions(hStoreColumnMapper.map(rs, "description", ctx)).build();
    }
}
