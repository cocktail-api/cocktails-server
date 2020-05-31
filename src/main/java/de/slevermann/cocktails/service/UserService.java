package de.slevermann.cocktails.service;

import de.slevermann.cocktails.dao.UserDao;
import de.slevermann.cocktails.dto.UserInfo;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.*;

@Service
@Transactional
@Validated
public class UserService {

    private final UserInfoMapper userInfoMapper;

    private final UserDao userDao;

    public UserService(UserInfoMapper userInfoMapper, UserDao userDao) {
        this.userInfoMapper = userInfoMapper;
        this.userDao = userDao;
    }

    public UserInfo getById(UUID uuid) {
        DbUserInfo dbUserInfo = userDao.getById(uuid);
        if (dbUserInfo == null) {
            throw new ResponseStatusException(NOT_FOUND);
        }
        return userInfoMapper.dbUserInfoToUserInfo(dbUserInfo);
    }

    public void updateNick(String newNick, UUID uuid) {
        if (userDao.nickExists(newNick)) {
            throw new ResponseStatusException(CONFLICT, "Nickname " + newNick + " is already taken");
        }
        int rowsAffected = userDao.updateNick(uuid, newNick);
        if (rowsAffected == 0) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
