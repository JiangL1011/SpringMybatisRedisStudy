package com.ling.jiang.mapper;

import com.ling.jiang.bean.UserRedPacket;
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
public interface UserRedPacketMapper {
    /**
     * 插入抢红包信息
     *
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    int grabRedPacket(@Param("userRedPacket") UserRedPacket userRedPacket);
}
