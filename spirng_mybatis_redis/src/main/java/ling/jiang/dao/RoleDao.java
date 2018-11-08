package ling.jiang.dao;

import ling.jiang.pojo.Role;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月08日
 * version: v1.0
 */
@Repository
public interface RoleDao {
    Role getRole(@Param("id") Long id);

    int deleteRole(@Param("id") Long id);

    int insertRole(@Param("role") Role role);

    int updateRole(@Param("role") Role role);

    List<Role> findRoles(@Param("roleName") String roleName, @Param("note") String note);
}
