package com.puhui.vo;

/**
 * Created by wentong on 2016/2/28.
 */
public class OrderCustom extends Orders {
    private String username;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return super.toString() + "OrderCustom{" +
                "username='" + username + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
