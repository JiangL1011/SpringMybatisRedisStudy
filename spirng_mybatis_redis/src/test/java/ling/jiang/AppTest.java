package ling.jiang;

import ling.jiang.pojo.Role;
import ling.jiang.service.RoleService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    private static ApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
    private static RoleService roleService = ctx.getBean(RoleService.class);

    @Test
    public void insert() {
        Role role = new Role("role_name_5", "role_note_5");
        System.out.println("成功插入数据：\r\n" + roleService.insertRole(role));
    }

    @Test
    public void getOne() {
        Role role = roleService.getRole(1L);
        System.out.println(role);
    }

    @Test
    public void updateOne() {
        Role role = new Role(1L, "role_update1", "role_note_update1");
        System.out.println("成功更新数据" + roleService.updateRole(role));
    }

    @Test
    public void deleteOne() {
        System.out.println("成功删除" + roleService.deleteRole(1L) + "条数据");
    }
}
