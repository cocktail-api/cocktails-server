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

    @AfterEach
    public void afterEach() {
        beforeEach();
    }

    protected void insertIngredientType(DbIngredientType type) {
        jdbi.useHandle(h ->
                h.createUpdate("INSERT INTO ingredient_type (id, name) VALUES (:id, :names)")
                        .bindBean(type)
                        .execute());
    }

    protected DbIngredientType loadIngredientType(String name) throws Exception {
        return builder.build().readValue(getClass()
                .getResource("/de/slevermann/cocktails/testdata/IngredientType/" + name), DbIngredientType.class);
    }

    protected Ingredient loadIngredient(String name) throws Exception {
        return builder.build().readValue(getClass()
                .getResource("/de/slevermann/cocktails/testdata/Ingredient/" + name), Ingredient.class);
    }

    protected void insertIngredient(Ingredient ingredient) {
        jdbi.useHandle(h ->
                h.createUpdate("INSERT INTO ingredient (id, ingredient_type_id, name, description)" +
                        "VALUES (:id, :type_id, :name, :description)")
                        .bind("id", ingredient.getId())
                        .bind("type_id", ingredient.getType().getId())
                        .bind("name", ingredient.getNames())
                        .bind("description", ingredient.getDescriptions())
                        .execute());
    }
}