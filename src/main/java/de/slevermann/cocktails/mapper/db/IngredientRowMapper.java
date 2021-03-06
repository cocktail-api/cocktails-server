package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.model.db.DbIngredient;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.db.DbModeration;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.jdbi.v3.postgres.HStoreColumnMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class IngredientRowMapper implements RowMapper<DbIngredient> {

    private final HStoreColumnMapper hStoreColumnMapper;

    private final ColumnMapper<DbModeration> moderationColumnMapper;

    public IngredientRowMapper(HStoreColumnMapper hStoreColumnMapper, ColumnMapper<DbModeration> moderationColumnMapper) {
        this.hStoreColumnMapper = hStoreColumnMapper;
        this.moderationColumnMapper = moderationColumnMapper;
    }

    @Override
    public DbIngredient map(ResultSet rs, StatementContext ctx) throws SQLException {
        DbIngredient.DbIngredientBuilder builder = DbIngredient.builder()
                .uuid(UUID.fromString(rs.getString("uuid")))
                .type(DbIngredientType.builder()
                        .uuid(UUID.fromString(rs.getString("type_uuid")))
                        .names(hStoreColumnMapper.map(rs, "type_name", ctx)).build())
                .names(hStoreColumnMapper.map(rs, "name", ctx))
                .descriptions(hStoreColumnMapper.map(rs, "description", ctx))
                .published(rs.getBoolean("published"))
                .moderation(moderationColumnMapper.map(rs, "moderation", ctx));
        String ownerUuid = rs.getString("owner_uuid");
        if (ownerUuid != null) {
            builder.userInfo(DbUserInfo.builder()
                    .uuid(UUID.fromString(ownerUuid))
                    .nick(rs.getString("owner_nick"))
                    .providerId(rs.getString("owner_provider_id")).build());
        }
        return builder.build();
    }
}
