package ling.jiang;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Hello world!
 */
public class App {
    private static ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring-redis.xml");
    private static RedisTemplate redisTemplate = ctx.getBean(RedisTemplate.class);

    public static void main(String[] args) {
        redisTemplate.convertAndSend("chat", "This is the first message!");
    }
}
