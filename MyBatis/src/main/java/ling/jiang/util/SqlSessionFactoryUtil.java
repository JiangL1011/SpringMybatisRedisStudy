package ling.jiang.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * description:
 * author:  JiangL
 * company:
 * date:    11/1/2018
 * version: v1.0
 */
public class SqlSessionFactoryUtil {
    private static SqlSessionFactory sqlSessionFactory;

    private SqlSessionFactoryUtil() {
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        if (sqlSessionFactory != null) return sqlSessionFactory;
        synchronized (SqlSessionFactoryUtil.class) {
            if (sqlSessionFactory != null) return sqlSessionFactory;
            String resource = "mybatis-config.xml";
            try (InputStream in = Resources.getResourceAsStream(resource)) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return sqlSessionFactory;
        }
    }

    public static SqlSession getSqlSession() {
        if (sqlSessionFactory == null) getSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}
