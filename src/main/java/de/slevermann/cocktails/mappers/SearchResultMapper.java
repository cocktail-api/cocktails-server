package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.SearchResult;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class SearchResultMapper implements RowMapper<SearchResult> {
    @Override
    public SearchResult map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new SearchResult()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .type(SearchResult.TypeEnum.fromValue(rs.getString("type")));
    }
}