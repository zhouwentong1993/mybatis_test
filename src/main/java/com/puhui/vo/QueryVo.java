package com.puhui.vo;

import java.util.List;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/13.
 * 复合查询
 */
public class QueryVo {
    private User user;

    private UserCustom userCustom;

    private List<Integer> ids;

    @Override
    public String toString() {
        return "QueryVo{" +
                "user=" + user +
                ", userCustom=" + userCustom +
                '}';
    }

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCustom getUserCustom() {
        return userCustom;
    }

    public void setUserCustom(UserCustom userCustom) {
        this.userCustom = userCustom;
    }
}
