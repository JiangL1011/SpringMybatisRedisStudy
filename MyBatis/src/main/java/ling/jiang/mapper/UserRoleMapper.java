package ling.jiang.mapper;

import ling.jiang.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    11/1/2018
 * version: v1.0
 */
public interface UserRoleMapper {
    UserRole getRoleByRoleId(@Param("roleId") Integer roleId);
}
