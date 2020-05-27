package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.model.db.DbUserInfo;
import de.slevermann.cocktails.model.UserInfo;
import org.springframework.stereotype.Component;

@Component
public class UserInfoMapper {

    public UserInfo dbUserInfoToUserInfo(DbUserInfo dbUserInfo) {
        return new UserInfo()
                .id(dbUserInfo.getUuid())
                .nick(dbUserInfo.getNick());
    }
}
