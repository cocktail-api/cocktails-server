package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.model.db.DbUserInfo;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.UUID;

@UseClasspathSqlLocator
public interface UserDao {

    @SqlQuery
    DbUserInfo getByProviderId(@Bind("providerId") String providerId);

    @SqlQuery
    DbUserInfo getById(@Bind("uuid") UUID uuid);

    @SqlUpdate
    @GetGeneratedKeys
    DbUserInfo create(@Bind("providerId") String providerId);

    @SqlUpdate
    void updateNick(@Bind("uuid") UUID uuid, @Bind("newNick") String newNick);

    @SqlQuery
    boolean nickExists(@Bind("nick") String nick);
}
