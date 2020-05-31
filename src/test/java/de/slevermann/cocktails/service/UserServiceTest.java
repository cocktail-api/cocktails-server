package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.UserDao;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpStatus.*;

@MockitoSettings
public class UserServiceTest {

    public final UserDao userDao = mock(UserDao.class);

    public final UserInfoMapper userInfoMapper = spy(new UserInfoMapper());

    public final UserService userService = new UserService(userInfoMapper, userDao);

    @Test
    public void testGetById() {
        when(userDao.getById(any())).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID()).providerId("providerId").build());
        assertNotNull(userService.getById(UUID.randomUUID()), "Service should return a user if the ID was found");
        verify(userInfoMapper, times(1)).dbUserInfoToUserInfo(any());
    }

    @Test
    public void testGetByIdNotFound() {
        when(userDao.getById(any())).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> userService.getById(UUID.randomUUID()), "Service should throw an exception if the ID was not found");
        assertEquals(NOT_FOUND, ex.getStatus(), "Response status should be 404");
        verify(userInfoMapper, times(0)).dbUserInfoToUserInfo(any());
    }

    @Test
    public void testUpdateNick() {
        when(userDao.nickExists(anyString())).thenReturn(false);
        when(userDao.updateNick(any(), any())).thenReturn(1);
        userService.updateNick("nick", UUID.randomUUID());
    }

    @Test
    public void testUpdateNickConflict() {
        when(userDao.nickExists(anyString())).thenReturn(true);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> userService.updateNick("nick", UUID.randomUUID()),
                "Nick conflict should throw an exception");
        assertEquals(CONFLICT, ex.getStatus(), "Http Status should be CONFLICT");
    }

    @Test
    public void testUpdateNickNotFound() {
        when(userDao.nickExists(anyString())).thenReturn(false);
        when(userDao.updateNick(any(), any())).thenReturn(0);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class,
                () -> userService.updateNick("nick", UUID.randomUUID()),
                "Nick conflict should throw an exception");
        assertEquals(NOT_FOUND, ex.getStatus(), "Http Status should be NOT FOUND");
    }
}
