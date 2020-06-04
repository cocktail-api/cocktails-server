package de.slevermann.cocktails.dao;

import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
public class UserDaoIT extends DaoTestBase {

    @Autowired
    private UserDao userDao;

    private static final String PROVIDER_ID = "PROVIDER_ID";

    private UUID uuid;

    @Test
    @Order(1)
    public void testCreateUser() {
        DbUserInfo createdUser = userDao.create(PROVIDER_ID);

        assertEquals(PROVIDER_ID, createdUser.getProviderId(),
                "Newly created user should have correct providerId");
        assertNotNull(createdUser.getUuid(), "Newly created user should have UUID");
        assertNull(createdUser.getNick(), "Newly created user should not have nick");

        uuid = createdUser.getUuid();
    }

    @Test
    @Order(2)
    public void testGetByProviderId() {
        DbUserInfo userInfo = userDao.getByProviderId(PROVIDER_ID);

        assertNotNull(userInfo, "User should be successfully fetched by provider ID");
    }

    @Test
    @Order(3)
    public void testGetByUuid() {
        DbUserInfo userInfo = userDao.getById(uuid);

        assertNotNull(userInfo, "User should be successfully fetched by UUID");
    }

    @Test
    @Order(4)
    public void testUpdateNick() {
        String nick = "john.doe";

        assertFalse(userDao.nickExists(nick), "Nick should not exist in database");

        userDao.updateNick(uuid, nick);

        assertTrue(userDao.nickExists(nick), "Nick should exist after creation");

        DbUserInfo userInfo = userDao.getById(uuid);

        assertEquals(nick, userInfo.getNick(), "Nick should be set for current user");
    }
}
