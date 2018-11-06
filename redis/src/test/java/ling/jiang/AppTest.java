package ling.jiang;

import ling.jiang.pojo.Role;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
    private static RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void test1() {
        Jedis jedis = new Jedis("localhost", 6379);
        int i = 0;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 1000) {
            jedis.set("test" + i, i + "");
            i++;
        }
        jedis.close();
        System.out.println(i);
    }

    @Test
    public void test2() {
        Role role = new Role();
        role.setId(1L);
        role.setNote("note_1");
        role.setRoleName("role_name_1");

        redisTemplate.opsForValue().set("role_1", role);

        Role role1 = (Role) redisTemplate.opsForValue().get("role_1");
        System.out.println(role1);
    }

    /**
     * 使用SessionCallback使得所有操作都来自于同一个redis连接池的同一个redis连接
     * 也可以使用RedisCallback
     */
    @Test
    public void test3() {
        final Role role = new Role();
        role.setId(1L);
        role.setNote("note_1");
        role.setRoleName("role_name_1");
        SessionCallback<Role> callback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations operations) throws DataAccessException {
                operations.boundValueOps("role_1").set(role);
                return (Role) (operations.boundValueOps("role_1").get());
            }
        };
        Role svedRole = (Role) redisTemplate.execute(callback);
        System.out.println(svedRole);
    }
}
