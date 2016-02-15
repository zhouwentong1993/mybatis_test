package com.puhui.test;

import java.io.InputStream;

/**
 * Created by zhouwentong1@gmail.com on 2016/2/10.
 */
public class SecondTest {
    public static void main(String[] args) {
        InputStream resourceAsStream = SecondTest.class.getClass().getClassLoader().getResourceAsStream("");
        System.out.println(resourceAsStream);
    }
}
