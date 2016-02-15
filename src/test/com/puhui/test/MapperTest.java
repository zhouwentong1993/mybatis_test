package com.puhui.test;

import com.puhui.mapper.UserMapper;
import com.puhui.vo.QueryVo;
import com.puhui.vo.User;
import junit.framework.TestCase;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/10.
 * Mapper 文件测试
 */
public class MapperTest extends TestCase {

    private SqlSessionFactory sqlSessionFactory;

    protected void setUp() throws Exception {
        //mybatis配置文件
        String resource = "sqlMapConfig.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //使用SqlSessionFactoryBuilder创建sessionFactory
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }


    public void testFindUserById() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //调用代理对象方法
        User user = userMapper.findUserById(1);
        System.out.println(user);
        //关闭session
        session.close();
    }

    @Test
    public void testFindUserByUsername() throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> userList = userMapper.findUserByUsername("张");
        System.out.println(userList.size());

    }

    public void testInsertUser() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        //要添加的数据
        User user = new User();
        user.setUsername("张三");
        user.setBirthday(new Date());
        user.setSex("1");
        user.setAddress("北京市");
        //通过mapper接口添加用户
        userMapper.insertUser(user);
        //提交
        session.commit();
        //关闭session
        session.close();
    }

    public void testParameterByMap() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        HashMap<String, Object> userMap = new HashMap<String, Object>();
        //要添加的数据
//        User user = new User();
//        user.setUsername("张三");
//        user.setBirthday(new Date());
//        user.setSex("1");
//        user.setAddress("北京市");
        userMap.put("id", 31);
        userMap.put("username", "张三");
        userMap.put("sex", "1");
        userMap.put("address", "北京市");
        //通过mapper接口添加用户
//        userMapper.insertUser(user);
        User user = userMapper.findUserByHashMap(userMap);
        System.out.println(user);
        //提交
//        session.commit();
        //关闭session
        session.close();
    }

    /**
     * 测试复合查询参数问题
     *
     * @throws Exception
     */
    public void testQueryVo() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
//        HashMap<String, Object> userMap = new HashMap<String,Object>();
        //要添加的数据
//        User user = new User();
//        user.setUsername("张三");
//        user.setBirthday(new Date());
//        user.setSex("1");
//        user.setAddress("北京市");
//        userMap.put("id", 31);
//        userMap.put("username", "张三");
//        userMap.put("sex", "1");
//        userMap.put("address", "北京市");
//        //通过mapper接口添加用户
////        userMapper.insertUser(user);
//        User user = userMapper.findUserByHashMap(userMap);
//        System.out.println(user);
        //提交
//        session.commit();
        //关闭session
        QueryVo queryVo = new QueryVo();
        User user = new User();
        user.setUsername("张");
        user.setId(1);
        queryVo.setUser(user);
        List<User> userList = userMapper.findUserByQueryVo(queryVo);
        System.out.println(userList.size());
        session.close();
    }

    public void testQueryCount() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
//        User user = new User();
        int userCount = userMapper.getAllUser(new User());
        System.out.println(userCount);
        session.close();
    }

    public void testManyCondition() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = userMapper.queryUserByManyCondition(1, "王五", "北京市");
        System.out.println(user);
        session.close();
    }

    @Test
    public void testQueryUserByIdList() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        QueryVo queryVo = new QueryVo();
        List<Integer> ids = new ArrayList<>();
        ids.add(1);
        ids.add(2);
        ids.add(10);
        ids.add(16);
        ids.add(22);
        ids.add(24);
        queryVo.setIds(ids);
        List<User> userList = userMapper.queryUserByIdList(queryVo);
        System.out.println(userList.size());
        session.close();
    }
    @Test
    public void testQueryUsersByUserList() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<User> users = new ArrayList<>();
        User user = new User();
        user.setId(1);
        User user1 = new User();
        user1.setId(2);
        User user2 = new User();
        user2.setId(10);
        User user3 = new User();
        user3.setId(16);
        users.add(user);
        users.add(user1);
        users.add(user2);
        users.add(user3);
        List<User> userList = userMapper.queryUsersByUserList(users);
        System.out.println(userList.get(0));
        System.out.println(userList.size());
        session.close();
    }

    /**
     * @throws Exception
     */
    public void testQueryMap() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        Map<String, Object> userMap = userMapper.getMapByQueryAllUser();
        Class<User> clazz = User.class;
        Field[] fields = clazz.getDeclaredFields();
        Map<String, Object> requiredMap = new HashMap<>(fields.length);
        if (fields.length > 0) {
            for (Field field : fields) {
                requiredMap.put(field.getName(), null);
            }
        }
        System.out.println(requiredMap.size());
        Map<String, Object> copyOfUserMap = new HashMap<>(userMap);
        Map<String, Object> copyOfRequiredMap = new HashMap<>(requiredMap);
        System.out.println(copyOfUserMap.size());
        for (Map.Entry entry1 : requiredMap.entrySet()) {
            for (Map.Entry entry2 : userMap.entrySet()) {
                if (!entry1.getKey().equals(entry2)) {
                    copyOfUserMap.put(entry1.getKey().toString(), null);
                } else {
                    break;
                }
            }
        }
        System.out.println(copyOfUserMap.size());
//        for (int i = 0; i < requiredMap.size(); i++) {
//            for (int j = 0; j < userMap.size(); j++) {
//                if (requiredMap.containsKey())
//            }
//        }
        for (Map.Entry entry : copyOfUserMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("=============");
        for (Map.Entry entry : userMap.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
        System.out.println("=============");
        System.out.println(userMap.size());
        session.close();
    }
}