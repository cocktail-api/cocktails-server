package de.slevermann.cocktails.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.slevermann.cocktails.api.UsersApi;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.dto.UserInfo;
import de.slevermann.cocktails.service.AuthenticationService;
import de.slevermann.cocktails.service.UserService;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserController implements UsersApi {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    private final UserInfoMapper userInfoMapper;

    public UserController(UserService userService, AuthenticationService authenticationService, UserInfoMapper userInfoMapper) {
        this.userService = userService;
        this.authenticationService = authenticationService;
        this.userInfoMapper = userInfoMapper;
    }

    @Override
    public Optional<ObjectMapper> getObjectMapper() {
        return Optional.empty();
    }

    @Override
    public Optional<HttpServletRequest> getRequest() {
        return Optional.empty();
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserInfo> getUserInfo() {
        return ResponseEntity.ok(userInfoMapper.dbUserInfoToUserInfo(authenticationService.getUserDetails()));
    }

    @Override
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Void> setNick(@Pattern(regexp = "^[\\p{L}0-9]+(\\.[\\p{L}0-9]+)*$") @Size(min = 1, max = 30) String body) {
        userService.updateNick(body, authenticationService.getUserDetails().getUuid());
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserInfo> getProfile(UUID uuid) {
        return ResponseEntity.ok(userService.getById(uuid));
    }
}
