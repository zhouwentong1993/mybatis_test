package com.puhui.mapper;

import com.puhui.vo.OrderCustom;
import com.puhui.vo.Orders;
import com.puhui.vo.QueryVo;
import com.puhui.vo.User;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/10.
 * UserMapper 用来替代 DAO
 */
public interface UserMapper {
    User findUserById(int id);

    User findUserByUsername(String username);

    void insertUser(@Param("user") User user);

    User findUserByHashMap(HashMap<String, Object> userMap);

    User findUserByQueryVo(QueryVo queryVo);

    int getAllUser(User user);

    User queryUserByManyCondition(@Param("id") int id, @Param("username") String username, @Param("address") String address);

    Map<String, Object> getMapByQueryAllUser();

    List<User> queryUserListByArray(Object[] userList);

    List<User> queryUserListByStringArray(String[] idList);

    List<OrderCustom> queryOrderList();

    List<Orders> queryOrderList2();

    List<Orders> queryOrderDetailList();

    List<User> queryOrderDetailList2();
}