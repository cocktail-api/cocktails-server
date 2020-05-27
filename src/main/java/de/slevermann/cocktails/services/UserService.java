package de.slevermann.cocktails.services;

import de.slevermann.cocktails.daos.UserDao;
import de.slevermann.cocktails.dbmodels.DbUserInfo;
import de.slevermann.cocktails.mapper.UserInfoMapper;
import de.slevermann.cocktails.models.UserInfo;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Pattern;
import java.util.UUID;

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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return userInfoMapper.dbUserInfoToUserInfo(dbUserInfo);
    }

    public void updateNick(@Pattern(regexp = "^[\\p{L}0-9]+(\\.[\\p{L}0-9]+)*$") @Length(min = 1, max = 30) String newNick,
                           UUID uuid) {
        if (userDao.nickExists(newNick)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Nickname " + newNick + " is already taken");
        }
        userDao.updateNick(uuid, newNick);
    }
}
