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
        int insertCount = roleService.insertRole(role);
        System.out.println("成功插入 " + insertCount + " 条数据");
    }

    @Test
    public void getOne() {
        Role role = roleService.getRole(1L);
        System.out.println(role);
    }
}
