package de.slevermann.cocktails.config;

import de.slevermann.cocktails.dao.IngredientDao;
import de.slevermann.cocktails.dao.IngredientTypeDao;
import de.slevermann.cocktails.dao.UserDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.enums.EnumStrategy;
import org.jdbi.v3.core.enums.Enums;
import org.jdbi.v3.core.mapper.ColumnMapper;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.postgres.HStoreColumnMapper;
import org.jdbi.v3.postgres.PostgresPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class JdbiConfiguration {

    @Bean
    public SqlObjectPlugin plugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    public PostgresPlugin postgresPlugin() {
        return new PostgresPlugin();
    }

    @Bean
    public HStoreColumnMapper hStoreColumnMapper() {
        return new HStoreColumnMapper();
    }

    @Bean
    public Jdbi jdbi(DataSource ds,
                     List<JdbiPlugin> jdbiPlugins,
                     List<RowMapper<?>> rowMappers,
                     List<ColumnMapper<?>> columnMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);
        columnMappers.forEach(jdbi::registerColumnMapper);
        jdbi.getConfig().get(Enums.class).setEnumStrategy(EnumStrategy.BY_NAME);
        return jdbi;
    }

    @Bean
    public IngredientTypeDao ingredientTypeDao(Jdbi jdbi) {
        return jdbi.onDemand(IngredientTypeDao.class);
    }

    @Bean
    public IngredientDao ingredientDao(Jdbi jdbi) {
        return jdbi.onDemand(IngredientDao.class);
    }

    @Bean
    public UserDao userDao(Jdbi jdbi) {
        return jdbi.onDemand(UserDao.class);
    }
}
