package com.ling.jiang.mapper;

import com.ling.jiang.bean.RedPacket;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
@Repository
public interface RedPacketMapper {
    /**
     * 获取红包信息
     *
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(@Param("id") Integer id);

    /**
     * 扣减抢红包数
     *
     * @param id 红包id
     * @return 更新记录条数
     */
    int decreaseRedPacket(@Param("id") Integer id, @Param("version") Integer version);
}
