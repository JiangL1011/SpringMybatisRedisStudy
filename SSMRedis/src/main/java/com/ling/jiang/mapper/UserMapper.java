package com.ling.jiang.mapper;

import com.ling.jiang.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月12日
 * version: v1.0
 */
@Repository
public interface UserMapper {
    List<User> getAllUsers();
}
