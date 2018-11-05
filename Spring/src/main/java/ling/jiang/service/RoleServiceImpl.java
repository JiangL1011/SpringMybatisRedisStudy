package ling.jiang.service;

import ling.jiang.pojo.Role;
import org.springframework.stereotype.Component;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月05日
 * version: v1.0
 */
@Component
public class RoleServiceImpl implements RoleService {
    @Override
    public void printRoleInfo(Role role) {
        assert role != null;
        System.out.println(role);
    }
}
