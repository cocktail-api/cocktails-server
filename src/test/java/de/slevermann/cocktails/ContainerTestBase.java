package de.slevermann.cocktails;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.PostgreSQLContainer;

@ContextConfiguration(initializers = ContainerTestBase.Initializer.class)
public abstract class ContainerTestBase {

    public static final PostgreSQLContainer postgres = new PostgreSQLContainer("postgres:12.2-alpine");

    static {
        if (System.getenv("NO_DOCKER") == null) {
            postgres.start();
        }
    }
    static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            if (System.getenv("NO_DOCKER") == null) {
                TestPropertyValues.of(
                        "spring.datasource.url=" + postgres.getJdbcUrl(),
                        "spring.datasource.username=" + postgres.getUsername(),
                        "spring.datasource.password=" + postgres.getPassword()
                ).applyTo(configurableApplicationContext.getEnvironment());
            }
        }
    }

}
