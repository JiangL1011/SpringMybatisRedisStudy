package com.ling.jiang.util;

import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    2018年11月14日
 * version: v1.0
 */
public class ReadLuaUtil {
    public static String read(String file) {
        try {
            return readUtil(new BufferedReader(new InputStreamReader(new ClassPathResource(file).getInputStream())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String readUtil(BufferedReader reader) throws IOException {
        StringBuffer sb = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\r\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(read("/redPacket.lua"));
    }
}
