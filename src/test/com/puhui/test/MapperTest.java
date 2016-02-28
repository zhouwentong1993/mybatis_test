package com.puhui.test;

import com.puhui.mapper.UserMapper;
import com.puhui.vo.OrderCustom;
import com.puhui.vo.QueryVo;
import com.puhui.vo.User;
import junit.framework.TestCase;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.KeyGenerator;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        User user = userMapper.findUserByUsername("张");
        System.out.println(user);

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
        HashMap<String, Object> userMap = new HashMap<String,Object>();
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
        user.setUsername("王五");
        user.setId(1);
        queryVo.setUser(user);
        User queryUser = userMapper.findUserByQueryVo(queryVo);
        System.out.println(queryUser);
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

    public void testQueryUserListByArray() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        User user = new User();
        user.setId(1);
        User user1 = new User();
        user1.setId(2);
        Object[] arr = new Object[2];
        arr[0] = user;
        arr[1] = user1;
        List<User> users = userMapper.queryUserListByArray(arr);
        System.out.println(users.size());
        session.close();
    }

    public void testQueryUserListByStringArray() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        String[] idList = {"1","2","1212"};
        List<User> userList = userMapper.queryUserListByStringArray(idList);
        System.out.println(userList.size());
        session.close();
    }
    public void testQueryOrderList() throws Exception {
        //获取session
        SqlSession session = sqlSessionFactory.openSession();
        //获取mapper接口的代理对象
        UserMapper userMapper = session.getMapper(UserMapper.class);
        List<OrderCustom> orderCustomList = userMapper.queryOrderList();
        orderCustomList.forEach(System.out::println);


        session.close();
    }

    /**
     *
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