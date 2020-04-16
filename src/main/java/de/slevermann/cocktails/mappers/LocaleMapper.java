package de.slevermann.cocktails.mappers;

import de.slevermann.cocktails.models.Locale;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public class LocaleMapper implements RowMapper<Locale> {

    /**
     * The offset of the unicode regional letters block, relative to the actual letters
     */
    private static final int REGIONAL_LETTER_OFFSET = 127397;

    @Override
    public Locale map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Locale()
                .language(rs.getString("language"))
                .country(rs.getString("country"))
                .name(rs.getString("name"))
                .localizedName(rs.getString("localized_name"))
                .flagEmoji(flagEmoji(rs.getString("country")));
    }

    public String flagEmoji(String input) {
        if (input.length() != 2) {
            return null;
        }
        input = input.toUpperCase();
        int[] codePoints = input.codePoints().map(x -> x + REGIONAL_LETTER_OFFSET).toArray();
        return new String(codePoints, 0, codePoints.length);
    }
}
