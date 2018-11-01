package ling.jiang;

import ling.jiang.mapper.UserMapper;
import ling.jiang.mapper.UserRoleMapper;
import ling.jiang.pojo.User;
import ling.jiang.pojo.UserRole;
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
    private UserRoleMapper roleMapper = sqlSession.getMapper(UserRoleMapper.class);

    @Test
    public void test1() {
        User user = userMapper.getUserById(3);
        System.out.println(user.getUserName());
        System.out.println(user.getUserRole());
    }

    @Test
    public void test2() {
        List<User> users = userMapper.getAllUsers();
        System.out.println(users);
    }

    @Test
    public void test3() {
        UserRole role = roleMapper.getRoleByRoleId(3);
        System.out.println(role);
        System.out.println(role);
    }

    @Test
    public void testCache() {
        SqlSession sqlSession1 = SqlSessionFactoryUtil.getSqlSession();
        SqlSession sqlSession2 = SqlSessionFactoryUtil.getSqlSession();
        UserRoleMapper roleMapper1 = sqlSession1.getMapper(UserRoleMapper.class);
        UserRoleMapper roleMapper2 = sqlSession2.getMapper(UserRoleMapper.class);
        UserRole role1 = roleMapper1.getRoleByRoleId(3);
        sqlSession1.commit();
        System.out.println("role1: " + role1);
        UserRole role2 = roleMapper2.getRoleByRoleId(3);
        sqlSession2.commit();
        System.out.println("role2: " + role2);
    }
}
