package team.web_first.mapper;

import team.web_first.javabean.User;
import org.apache.ibatis.annotations.Param;

/**
 * Mapping interface
 * UserMapper.xml
 */

public interface UserMapper {
    User getUserByName(String userName);

    User getUserByAbs(@Param("userName") String userName, @Param("userPassword") String userPassword);

    int addUser(User user);
}
