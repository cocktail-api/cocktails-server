package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.ContainerTestBase;
import de.slevermann.cocktails.JdbiTest;
import org.jdbi.v3.core.Jdbi;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;

/*
 * Test base class for testing DAOs. Automatically provides a clean
 * database for testing, and cleans the database again after tests are through.
 */
@JdbiTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class DaoTestBase extends ContainerTestBase {

    @Autowired
    protected Jdbi jdbi;

    private void truncate(Jdbi jdbi) {
        jdbi.useHandle(h -> {
            h.execute("TRUNCATE TABLE ingredient CASCADE");
            h.execute("TRUNCATE TABLE ingredient_type CASCADE");
            h.execute("TRUNCATE TABLE \"user\" CASCADE");
        });
    }

    @BeforeAll
    public final void beforeAll() {
        truncate(jdbi);
        customInit();
    }

    @AfterAll
    public final void afterAll() {
        truncate(jdbi);
        customTearDown();
    }

    /**
     * Implement this method to perform any tasks needed before tests are started
     */
    protected void customInit() {

    }

    /**
     * Implement this method to perform any tasks needed after the tests are done
     */
    protected void customTearDown() {

    }
}
