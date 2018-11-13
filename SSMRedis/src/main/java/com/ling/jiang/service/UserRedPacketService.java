package com.ling.jiang.service;

import com.ling.jiang.bean.UserRedPacket;

import java.util.List;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
public interface UserRedPacketService {
    /**
     * 保存抢红包信息
     *
     * @param redPacketId 红包编号
     * @param userId      用户编号
     * @return 影响记录数
     */
    int grabRedPacket(int redPacketId, int userId);

    int redisRedPacket(List<UserRedPacket> userRedPackets);

    /**
     * 通过redis实现抢红包
     *
     * @param redPacketId 红包编号
     * @param userId      用户编号
     * @return 0-没有库存，失败；1-成功，且不是最后一个红包；2-成功，且是最后一个红包
     */
    int grabRedPacketByRedis(int redPacketId, int userId);
}
