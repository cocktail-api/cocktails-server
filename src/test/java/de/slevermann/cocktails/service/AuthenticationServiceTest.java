package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.UserDao;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@MockitoSettings
public class AuthenticationServiceTest {

    private final UserDao userDao = mock(UserDao.class);

    private final SecurityContextService securityContextService = mock(SecurityContextService.class);

    private final SecurityContext securityContext = mock(SecurityContext.class);

    private final AuthenticationService authenticationService = new AuthenticationService(userDao, securityContextService);

    private static final Jwt JWT = Jwt.withTokenValue("mockToken")
            .claim("uid", "providerId")
            .subject("sub")
            .header("header", "value").build();

    private static final JwtAuthenticationToken TOKEN = new JwtAuthenticationToken(JWT);

    @BeforeEach
    public void before() {
        when(securityContextService.getSecurityContext()).thenReturn(securityContext);
    }

    @Test
    public void testGetJwt() {
        when(securityContext.getAuthentication()).thenReturn(TOKEN);
        JwtAuthenticationToken authToken = authenticationService.getJwt();

        assertEquals(TOKEN, authToken, "Returned token should match");
    }

    @Test
    public void testGetJwtNoToken() {
        when(securityContext.getAuthentication()).thenReturn(null);

        assertNull(authenticationService.getJwt(), "A null token from context should return a null token");
    }

    @Test
    public void testGetUserDetails() {
        when(securityContext.getAuthentication()).thenReturn(TOKEN);
        when(userDao.getByProviderId(any())).thenReturn(DbUserInfo.builder()
                .uuid(UUID.randomUUID()).providerId("providerId").build());

        assertNotNull(authenticationService.getUserDetails(), "With a valid auth token, userDetails should not be null");
    }

    @Test
    public void testGetUserDetailsNoToken() {
        when(securityContext.getAuthentication()).thenReturn(null);

        assertNull(authenticationService.getUserDetails(), "Without a valid token, user details should be null");
    }

}
