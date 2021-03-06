package de.slevermann.cocktails.mapper.db;

import de.slevermann.cocktails.dto.SearchResult;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Mapper
public class SearchResultRowMapper implements RowMapper<SearchResult> {
    @Override
    public SearchResult map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new SearchResult()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .type(SearchResult.TypeEnum.fromValue(rs.getString("type")));
    }
}
