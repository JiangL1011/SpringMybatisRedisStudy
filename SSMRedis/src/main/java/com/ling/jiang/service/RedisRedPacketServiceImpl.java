package com.ling.jiang.service;

import com.ling.jiang.bean.UserRedPacket;
import com.ling.jiang.util.ReadLuaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    private static final String PREFIX = "red_packet_list_";
    // 每次取出100条，避免一次取出消耗太多内存
    private static final int TIME_SIZE = 100;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserRedPacketService userRedPacketService;

    @Override
    // 开启新线程运行
    @Async
    public void saveUserRedPacketByRedis(Integer redPacketId, Double unitAmount) {
        System.err.println(Thread.currentThread().getName() + "\tStart saving data......");
        long start = System.currentTimeMillis();
        // 获取列表操作对象
        BoundListOperations<String, Object> ops = redisTemplate.boundListOps(
                PREFIX + redPacketId);
        Long size = ops.size();
//        long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
        double times = Math.ceil(size / TIME_SIZE);
        int count = 0;
        List<UserRedPacket> userRedPackets = new ArrayList<>(TIME_SIZE);
        for (int i = 0; i < times; i++) {
            // 获取至多TIME_SIZE个抢红包信息
            List<Object> userIdList = null;
            if (i == 0) {
                userIdList = ops.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPackets.clear();
            // 保存红包信息
            for (Object anUserIdList : userIdList) {
                String args = anUserIdList.toString();
                String[] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                int userId = Integer.parseInt(userIdStr);
                long time = Long.parseLong(timeStr);
                // 生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote("抢红包 " + redPacketId);
                userRedPackets.add(userRedPacket);
            }
            // 插入抢红包信息
            count += userRedPacketService.redisRedPacket(userRedPackets);
        }
        // 删除Redis列表
        redisTemplate.delete(PREFIX + redPacketId);
        long end = System.currentTimeMillis();
        System.err.println("End saving, cost " + (end - start) + " ms, " + count + " data have been saved.");
    }

    @Override
    public void initUserRedPacketByRedis(String luaFile) {
        String script = ReadLuaUtil.read(luaFile);
        // 获取底层Redis操作对象
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        String sha1 = jedis.scriptLoad(script);
        jedis.evalsha(sha1);
    }
}
