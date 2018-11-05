package ling.jiang.service;

import ling.jiang.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月05日
 * version: v1.0
 */
@Component
public class RoleServiceImpl2 implements RoleService2 {
    @Autowired
    Role role;

    @Override
    public void printRoleInfo() {
        System.out.println(role);
    }
}
