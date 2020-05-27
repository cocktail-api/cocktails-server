package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.model.db.DbUserInfo;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class UserInfoRowMapper implements RowMapper<DbUserInfo> {

    @Override
    public DbUserInfo map(ResultSet rs, StatementContext ctx) throws SQLException {
        return DbUserInfo.builder()
                .uuid(UUID.fromString(rs.getString("uuid")))
                .providerId(rs.getString("provider_id"))
                .nick(rs.getString("nick")).build();
    }
}
