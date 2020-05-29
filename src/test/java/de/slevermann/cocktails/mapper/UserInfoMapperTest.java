package de.slevermann.cocktails.mapper;

import de.slevermann.cocktails.dto.UserInfo;
import de.slevermann.cocktails.model.db.DbUserInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoMapperTest {

    public final UserInfoMapper userInfoMapper = new UserInfoMapper();

    @ParameterizedTest
    @MethodSource("provider")
    public void testMappings(DbUserInfo dbUserInfo) {
        UserInfo userInfo = userInfoMapper.dbUserInfoToUserInfo(dbUserInfo);

        assertEquals(dbUserInfo.getUuid(), userInfo.getId(), "UUID of mapped user should match");
        assertEquals(dbUserInfo.getNick(), userInfo.getNick(), "Nick of mapped user should match");
    }

    private static Stream<Arguments> provider() {
        return Stream.of(
                Arguments.of(DbUserInfo.builder()
                        .nick(null)
                        .uuid(UUID.randomUUID())
                        .providerId("providerId").build()),
                Arguments.of(DbUserInfo.builder()
                        .nick("john.doe")
                        .uuid(UUID.randomUUID())
                        .providerId("providerId").build())
        );
    }
}
