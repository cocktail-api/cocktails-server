package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.model.db.DbIngredientType;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.HStoreColumnMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class IngredientTypeRowMapper implements RowMapper<DbIngredientType> {

    private final HStoreColumnMapper hStoreColumnMapper;

    public IngredientTypeRowMapper(HStoreColumnMapper hStoreColumnMapper) {
        this.hStoreColumnMapper = hStoreColumnMapper;
    }

    @Override
    public DbIngredientType map(ResultSet rs, StatementContext ctx) throws SQLException {
        return DbIngredientType.builder()
                .uuid(UUID.fromString(rs.getString("uuid")))
                .names(hStoreColumnMapper.map(rs, "name", ctx)).build();
    }
}
