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

    public AuthenticationService(UserDao userDao) {
        this.userDao = userDao;
    }

    public JwtAuthenticationToken getJwt() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof JwtAuthenticationToken) {
            return (JwtAuthenticationToken) authentication;
        } else {
            return null;
        }
    }

    public DbUserInfo getUserDetails() {
        JwtAuthenticationToken jwt = getJwt();

        String providerId = (String) jwt.getTokenAttributes().get("uid");

        return userDao.getByProviderId(providerId);
    }
}
