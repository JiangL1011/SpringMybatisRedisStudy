package ling.jiang;

import ling.jiang.mapper.UserMapper;
import ling.jiang.pojo.User;
import ling.jiang.util.SqlSessionFactoryUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    private SqlSession sqlSession = SqlSessionFactoryUtil.getSqlSession();
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    @Test
    public void test1() {
        User user = userMapper.getUserById(3);
        System.out.println(user.getUserName());
    }

    @Test
    public void test2() {
        List<User> users = userMapper.getAllUsers();
        System.out.println(users);
    }
}
