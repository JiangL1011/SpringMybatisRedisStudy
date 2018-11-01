package ling.jiang.mapper;

import ling.jiang.pojo.User;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    11/1/2018
 * version: v1.0
 */
public interface UserMapper {
    User getUserById(Integer id);

    List<User> getAllUsers();
}
