package ling.jiang;

import ling.jiang.pojo.Role;
import ling.jiang.service.RoleService;
import ling.jiang.service.RoleService2;
import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);

    @Test
    public void test1() {
        Role role = null;
        try {
            role = ctx.getBean(Role.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        System.out.println(role);
    }


    @Test
    public void test2() {
        RoleService roleService = null;
        Role role = null;
        try {
            roleService = ctx.getBean(RoleService.class);
            role = ctx.getBean(Role.class);
        } catch (BeansException e) {
            e.printStackTrace();
        }
        roleService.printRoleInfo(role);
        /*System.out.println("########################");
        roleService.printRoleInfo(null);*/
    }

    @Test
    public void test3() {
        RoleService2 roleService2 = ctx.getBean(RoleService2.class);
        roleService2.printRoleInfo();
    }
}
