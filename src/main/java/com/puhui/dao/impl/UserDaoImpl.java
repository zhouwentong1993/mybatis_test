package com.puhui.dao.impl;

import com.puhui.dao.UserDao;
import com.puhui.vo.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/10.
 */
public class UserDaoImpl implements UserDao {

    private SqlSessionFactory sqlSessionFactory;

    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> findAllUser() {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        List<User> userList = null;
        try {
            userList = sqlSession.selectList("test.findAllUser");
        } finally {
            sqlSession.close();
        }
        return userList == null ? Collections.<User>emptyList() : userList;
    }

    @Override
    public User findUserById(int id) {
        SqlSession sqlSession = this.sqlSessionFactory.openSession();
        User user = null;
        try {
            user = sqlSession.selectOne("test.findUserById", id);
        } finally {
            sqlSession.close();
        }
        return user;
    }
}
