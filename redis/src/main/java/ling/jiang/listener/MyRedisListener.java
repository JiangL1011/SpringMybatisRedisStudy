package ling.jiang.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月06日
 * version: v1.0
 */
public class MyRedisListener implements MessageListener {
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
//        获取消息
        byte[] body = message.getBody();
//        使用值序列化器转换
        String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
        System.out.println("msgBody: " + msgBody);
//        获取channel
        byte[] channel = message.getChannel();
//        使用字符串序列化转换器
        String channelStr = (String) getRedisTemplate().getStringSerializer().deserialize(channel);
        System.out.println("channelStr: " + channelStr);
//        渠道名称转换
        String patternStr = new String(pattern);
        System.out.println("patternStr: " + patternStr);
    }
}
