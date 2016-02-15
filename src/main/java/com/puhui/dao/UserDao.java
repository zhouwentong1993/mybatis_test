package com.puhui.dao;

import com.puhui.vo.User;

import java.util.List;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/10.
 * UserDAO 接口
 */
public interface UserDao {
    List<User> findAllUser();

    User findUserById(int id);
}
