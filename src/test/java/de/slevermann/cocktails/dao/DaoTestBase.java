package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.ContainerTestBase;
import de.slevermann.cocktails.model.db.DbIngredientType;
import de.slevermann.cocktails.model.Ingredient;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public abstract class DaoTestBase extends ContainerTestBase {

    protected final Jdbi jdbi;

    protected final Jackson2ObjectMapperBuilder builder;

    protected DaoTestBase(Jdbi jdbi, Jackson2ObjectMapperBuilder builder) {
        this.jdbi = jdbi;
        this.builder = builder;
    }

    @BeforeEach
    public void beforeEach() {
        jdbi.useHandle(h -> {
            h.execute("TRUNCATE TABLE ingredient CASCADE");
            h.execute("TRUNCATE TABLE ingredient_type CASCADE");
        });
    }
}
