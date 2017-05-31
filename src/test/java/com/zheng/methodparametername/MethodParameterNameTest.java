package com.zheng.methodparametername;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class MethodParameterNameTest {

    /**
     * 如果不使用-parameters参数来编译这个类，将会得到：
     * Parameter: arg0
     * 使用-parameters编译类，那么方法的真实名字将会打印出来
     * maven-compiler-plugin添加-parameters
     * Parameter: str
     * @throws NoSuchMethodException
     */
    @Test
    public void testMethodName() throws NoSuchMethodException {
        Method method = MethodParameterName.class.getMethod("sayHello", String.class);
        Arrays.asList(method.getParameters()).forEach(parameter->{
            System.out.println("Parameter: " + parameter.getName());
        });
    }
}
