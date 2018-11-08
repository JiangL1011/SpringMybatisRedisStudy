package ling.jiang;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月09日
 * version: v1.0
 */
@Configuration
@EnableCaching
public class RedisConfig {
    @Bean(name = "redisTemplate")
    public RedisTemplate initRedisTemplate() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        最大空闲数
        poolConfig.setMaxIdle(50);
//        最大连接数
        poolConfig.setMaxTotal(100);
//        最大等待毫秒数
        poolConfig.setMaxWaitMillis(20000);
//        创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
//        调用后初始化风法，没有它将抛出异常
        connectionFactory.afterPropertiesSet();
//        自定义Redis序列化器
        JdkSerializationRedisSerializer jdkSerializationRedisSerializer = new JdkSerializationRedisSerializer();
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
//        定义RedisTemplate，并设置连接工程
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(connectionFactory);
//        设置序列化器
        redisTemplate.setDefaultSerializer(stringRedisSerializer);
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(jdkSerializationRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(jdkSerializationRedisSerializer);
        return redisTemplate;
    }

    @Bean(name = "redisCacheManager")
    public CacheManager initRedisCacheManager() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        最大空闲数
        poolConfig.setMaxIdle(50);
//        最大连接数
        poolConfig.setMaxTotal(100);
//        最大等待毫秒数
        poolConfig.setMaxWaitMillis(20000);
//        创建Jedis连接工厂
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory(poolConfig);
        connectionFactory.setHostName("localhost");
        connectionFactory.setPort(6379);
//        调用后初始化风法，没有它将抛出异常
        connectionFactory.afterPropertiesSet();
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//        设置缓存有效期为1小时
        configuration.entryTtl(Duration.ofHours(1));
        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory))
                .cacheDefaults(configuration).build();
    }

}
