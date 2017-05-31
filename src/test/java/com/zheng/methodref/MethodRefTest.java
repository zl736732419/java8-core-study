package com.zheng.methodref;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class MethodRefTest {

    private List<Car> cars = Lists.newArrayList();
    
    /**
     * Class::new, Class<T>::new
     * 构造器引用,当前构造器没有参数
     */
    @Before
    public void testConstrucatorRef() {
        Car car = Car.create(Car::new);
        cars = Arrays.asList(car);
    }

    /**
     * Class::static_method
     * 静态方法引用
     * 需要接受一个类对象参数
     */
    @Test
    public void testStaticMethodRef() {
        cars.forEach(Car::collide);
    }

    /**
     * 特定了类的任意实例方法
     * 这个方法没有参数，使用this指向当前对象
     */
    @Test
    public void testClassMethod() {
        cars.forEach(Car::repair);
    }

    /**
     * 实例的实例方法
     * 需要接受一个类对象参数
     */
    @Test
    public void testInstanceMethod() {
        Car car = Car.create(Car::new);
        cars.forEach(car::follow);
    }
    
}
