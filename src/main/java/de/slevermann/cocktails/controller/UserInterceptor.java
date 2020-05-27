package de.slevermann.cocktails.controller;

import de.slevermann.cocktails.dao.UserDao;
import de.slevermann.cocktails.model.db.DbUserInfo;
import de.slevermann.cocktails.service.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class UserInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    private final AuthenticationService authenticationService;

    private final UserDao userDao;

    public UserInterceptor(AuthenticationService authenticationService, UserDao userDao) {
        this.authenticationService = authenticationService;
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        JwtAuthenticationToken authenticationToken = authenticationService.getJwt();
        if (authenticationToken != null) {
            Object providerId = authenticationToken.getTokenAttributes().get("uid");
            if (providerId instanceof String) {
                String id = (String) providerId;

                DbUserInfo dbUserInfo = userDao.getByProviderId(id);

                if (dbUserInfo == null) {
                    DbUserInfo createdUser = userDao.create(id);
                    LOGGER.info("Created new user in DB {}", createdUser);
                }
            }
        }
        return true;
    }
}
