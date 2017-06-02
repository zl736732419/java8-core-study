package com.zheng.methodref;

import java.util.function.Supplier;

/**
 * 对于方法引用，并不能单独存在，需要结合Supplier或者Consumer才能操作
 * 4种方法引用
 * Created by zhenglian on 2017/5/30.
 */
public class Car {
    
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }
    
    public static void collide(final Car car) {
        System.out.println("collided:" + car.toString());
    }

    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }

    public void repair() {
        System.out.println( "Repaired " + this.toString() );
    }
}
