package com.zheng.interfacemethod;

import java.util.function.Supplier;

/**
 * 接口的默认方法和静态方法
 * Created by zhenglian on 2017/5/30.
 */
public interface Defaultable {
    
    void sayHello(String str);

    /**
     * 静态方法
     * @param supplier
     * @return
     */
    static Defaultable create(Supplier<Defaultable> supplier) {
        return supplier.get();
    }

    /**
     * 静态方法
     */
    static void sayHello() {System.out.println("hello");}
    
    /**
     * 接口的默认实现方法
     * 但是我们在定义默认方法的时候一定要慎重考虑，是否真的有必要引用默认方法
     * @return
     */
    default String notRequired() {
        return "Default Implement";
    }
}
