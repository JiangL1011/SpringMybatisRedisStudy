package ling.jiang.mapper;

import ling.jiang.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    11/1/2018
 * version: v1.0
 */
public interface UserMapper {
    User getUserById(@Param("id") Integer id);

    List<User> getAllUsers();

    List<User> getUsersByRoleId(@Param("roleId") Integer roleId);
}
