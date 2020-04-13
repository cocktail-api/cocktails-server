package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.LocalizedString;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class LocalizedStringMapper implements RowMapper<LocalizedString> {
    @Override
    public LocalizedString map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new LocalizedString()
                .id(rs.getLong("id"))
                .locale(rs.getString("locale"))
                .string(rs.getString("string"));
    }
}
