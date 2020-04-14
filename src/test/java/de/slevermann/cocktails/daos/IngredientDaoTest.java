package de.slevermann.cocktails.daos;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@SpringBootTest
public class IngredientDaoTest {

    @Autowired
    private Jdbi jdbi;

    @Autowired
    private IngredientDao dao;

    @BeforeEach
    public void beforeEach() {
        try (Handle h = jdbi.open()) {
            h.execute("TRUNCATE ingredient, ingredient_data, ingredient_type, ingredient_type_name, language CASCADE");
            assertEquals(0, h.createQuery("SELECT COUNT(*) FROM ingredient").mapTo(Long.class).first(), "Database should be empty at beginning of each test");
            assertEquals(0, h.createQuery("SELECT COUNT(*) FROM ingredient_data").mapTo(Long.class).first(), "Database should be empty at beginning of each test");
            assertEquals(0, h.createQuery("SELECT COUNT(*) FROM ingredient_type").mapTo(Long.class).first(), "Database should be empty at beginning of each test");
            assertEquals(0, h.createQuery("SELECT COUNT(*) FROM ingredient_type_name").mapTo(Long.class).first(), "Database should be empty at beginning of each test");
            assertEquals(0, h.createQuery("SELECT COUNT(*) FROM language").mapTo(Long.class).first(), "Database should be empty at beginning of each test");
        }
    }
}
