package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.UserDao;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UserDao userDao;

    private final SecurityContextService securityContextService;

    public AuthenticationService(UserDao userDao, SecurityContextService securityContextService) {
        this.userDao = userDao;
        this.securityContextService = securityContextService;
    }

    public JwtAuthenticationToken getJwt() {
        Authentication authentication = securityContextService.getSecurityContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            return (JwtAuthenticationToken) authentication;
        } else {
            return null;
        }
    }

    public DbUserInfo getUserDetails() {
        JwtAuthenticationToken jwt = getJwt();

        if (jwt == null) {
            return null;
        }

        String providerId = (String) jwt.getTokenAttributes().get("uid");

        return userDao.getByProviderId(providerId);
    }
}
