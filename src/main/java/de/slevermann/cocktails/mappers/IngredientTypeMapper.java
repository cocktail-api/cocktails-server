package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.dbmodels.DbIngredientType;
import de.slevermann.cocktails.models.IngredientType;
import de.slevermann.cocktails.models.TranslatedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.HStoreColumnMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class IngredientTypeMapper implements RowMapper<DbIngredientType> {

    private final HStoreColumnMapper hStoreColumnMapper;

    public IngredientTypeMapper(HStoreColumnMapper hStoreColumnMapper) {
        this.hStoreColumnMapper = hStoreColumnMapper;
    }

    @Override
    public DbIngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return DbIngredientType.builder()
                .id(rs.getLong("id"))
                .names(hStoreColumnMapper.map(rs, "name", ctx)).build();
    }
}
