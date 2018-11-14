package com.ling.jiang.controller;

import com.ling.jiang.service.RedisRedPacketService;
import com.ling.jiang.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月13日
 * version: v1.0
 */
@Controller
public class UserRedPacketController {
    @Autowired
    private UserRedPacketService userRedPacketService;
    @Autowired
    private RedisRedPacketService redisRedPacketService;

    @RequestMapping("redPacket")
    public String redPacket() {
        return "redPacket";
    }

    @RequestMapping("grabRedPacket")
    @ResponseBody
    public Map<String, Object> grabRedPacket(Integer redPacketId, Integer userId) {
//        int result = userRedPacketService.grabRedPacket(redPacketId, userId);

        int result = userRedPacketService.grabRedPacketByRedis(redPacketId, userId);

        Map<String, Object> map = new HashMap<>();
        boolean flag = result > 0;
        map.put("success", flag);
        map.put("message", userId + (flag ? "\t抢红包成功" : "\t抢红包失败"));
        return map;
    }

    @RequestMapping("sendRedPacket")
    @ResponseBody
    public boolean sendRedPacket() {
        //初始化redis数据，模拟发红包
        redisRedPacketService.initUserRedPacketByRedis("redPacketInit.lua");
        return true;
    }
}
