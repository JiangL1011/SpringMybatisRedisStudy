package ling.jiang.service;

import ling.jiang.pojo.Role;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月08日
 * version: v1.0
 */
public interface RoleService {
    Role getRole(Long id);

    int deleteRole(Long id);

    int insertRole(Role role);

    int updateRole(Role role);

    List<Role> findRoles(String roleName, String note);
}
