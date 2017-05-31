package com.zheng.interfacemethod;

import org.junit.Test;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class InterfaceTest {
    
    @Test
    public void testInterface() {
        DefaultableImpl impl = (DefaultableImpl) Defaultable.create(DefaultableImpl::new);
        impl.sayHello("hello world");
        System.out.println(impl.notRequired());
    }
    
}
