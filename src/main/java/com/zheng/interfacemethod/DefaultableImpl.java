package com.zheng.interfacemethod;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class DefaultableImpl implements Defaultable {

    @Override
    public void sayHello(String str) {
        System.out.println(str);
    }
}
