package de.slevermann.cocktails.config;

import de.slevermann.cocktails.daos.IngredientDao;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.enums.EnumStrategy;
import org.jdbi.v3.core.enums.Enums;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
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
    public Jdbi jdbi(DataSource ds, List<JdbiPlugin> jdbiPlugins, List<RowMapper<?>> rowMappers) {
        TransactionAwareDataSourceProxy proxy = new TransactionAwareDataSourceProxy(ds);
        Jdbi jdbi = Jdbi.create(proxy);
        jdbiPlugins.forEach(jdbi::installPlugin);
        rowMappers.forEach(jdbi::registerRowMapper);
        jdbi.getConfig().get(Enums.class).setEnumStrategy(EnumStrategy.BY_NAME);
        return jdbi;
    }

    @Bean
    public IngredientDao ingredientDao(Jdbi jdbi) {
        return jdbi.onDemand(IngredientDao.class);
    }
}
