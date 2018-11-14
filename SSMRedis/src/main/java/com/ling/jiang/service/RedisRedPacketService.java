package com.ling.jiang.service;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
public interface RedisRedPacketService {
    void saveUserRedPacketByRedis(Integer redPacketId, Double unitAmount);

    void initUserRedPacketByRedis(String luaFile);
}
