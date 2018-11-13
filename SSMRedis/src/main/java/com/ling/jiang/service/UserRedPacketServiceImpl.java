package com.ling.jiang.service;

import com.ling.jiang.bean.RedPacket;
import com.ling.jiang.bean.UserRedPacket;
import com.ling.jiang.mapper.RedPacketMapper;
import com.ling.jiang.mapper.UserRedPacketMapper;
import com.ling.jiang.util.ReadLuaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    private UserRedPacketMapper userRedPacketMapper;
    @Autowired
    private RedPacketMapper redPacketMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private RedisRedPacketService redisRedPacketService;

    private static final int FAILED = 0;

    // Lua脚本
    private String script = ReadLuaUtil.read("redPacket.lua");
    // 在缓存Lua脚本后，使用该变量保存Redis返回的32位的SHA1编码，使用它去执行缓存的Lua脚本
    private String sha1;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacket(int redPacketId, int userId) {
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) return FAILED;
//        for (int i = 0; i < 3; i++) {
            // 获取红包信息
            RedPacket redPacket = redPacketMapper.getRedPacket(redPacketId);
            // 红包库存大于0
            if (redPacket.getStock() > 0) {
                int update = redPacketMapper.decreaseRedPacket(redPacketId, redPacket.getVersion());
                if (update == 0) continue;
                // 生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包 " + redPacketId);
                // 插入抢红包信息
                return userRedPacketMapper.grabRedPacket(userRedPacket);
            }
        }
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int redisRedPacket(List<UserRedPacket> userRedPackets) {
        int count = 0;
        for (UserRedPacket userRedPacket : userRedPackets) {
            count += userRedPacketMapper.grabRedPacket(userRedPacket);
        }
        return count;
    }

    @Override
    public int grabRedPacketByRedis(int redPacketId, int userId) {
        // 当前抢红包用户和日期信息
        String args = userId + "-" + System.currentTimeMillis();
        int result;
        // 获取底层Redis操作对象
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        try {
            // 如果脚本没有加载过，那么进行加载，这样会返回一个sha1编码
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(script);
            }
            // 执行脚本，返回结果
            result = (Integer) jedis.evalsha(sha1, 1, redPacketId + "", args);
            // 返回2时为最后一个红包，此时将抢红包信息通过异步保存到数据库中
            if (result == 2) {
                // 获取单个小红包金额
                String unitAmountStr = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                // 触发保存数据库操作
                double unitAmount = Double.parseDouble(unitAmountStr);
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
            }
        } finally {
            // 确保jedis顺利关闭
            if (jedis.isConnected()) jedis.close();
        }
        return result;
    }
}
