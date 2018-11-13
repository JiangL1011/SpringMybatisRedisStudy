package com.ling.jiang.service;

import com.ling.jiang.bean.RedPacket;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
public interface RedPacketService {
    /**
     * 获取红包信息
     *
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Integer id);

    /**
     * 扣减抢红包数
     *
     * @param id 红包id
     * @return 更新记录条数
     */
    int decreaseRedPacket(Integer id, Integer version);
}
