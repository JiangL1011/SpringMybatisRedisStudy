package ling.jiang;

import ling.jiang.pojo.Role;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

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
        Logger logger = LoggerFactory.getLogger(App.class);
        final Role role = new Role();
        role.setId(1L);
        role.setNote("note_1");
        role.setRoleName("role_name_1");
        SessionCallback<Role> callback = new SessionCallback<Role>() {
            @Override
            public Role execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.boundValueOps("role_1").set(role);
                operations.exec();
                return (Role) (operations.boundValueOps("role_1").get());
            }
        };
        Role savedRole = (Role) redisTemplate.execute(callback);
        logger.info(savedRole.toString());
        System.out.println(savedRole);
    }

    @Test
    public void test4() {
        Jedis jedis = new Jedis("localhost", 6379);
        Pipeline pipeline = jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            pipeline.set("pipeline_key_" + i, "pipeline_value_" + i);
            pipeline.get("pipeline_key_" + i);
        }
        long end = System.currentTimeMillis();
//        该方法只执行同步，不返回结果
//        pipeline.sync();
        List<Object> list = pipeline.syncAndReturnAll();
        System.out.println(list.size());
        System.out.println("耗时：" + (end - start) + " 毫秒");
    }

    @Test
    public void test5() {
        SessionCallback sessionCallback = new SessionCallback<String>() {
            @Override
            public String execute(RedisOperations ops) throws DataAccessException {
                for (int i = 0; i < 100000; i++) {
                    ops.boundValueOps("pipeline_key_" + i).set("pipeline_value_" + i);
                    ops.boundValueOps("pipeline_key_" + i).get();
                }
                return null;
            }
        };
        long start = System.currentTimeMillis();
        List list = redisTemplate.executePipelined(sessionCallback);
        long end = System.currentTimeMillis();
        System.out.println(list.size());
        System.out.println(list.get(2));
        System.out.println("耗时：" + (end - start) + " 毫秒");
    }

    @Test
    public void test6() {
        redisTemplate.convertAndSend("chat", "This is the first message!");
    }
}
