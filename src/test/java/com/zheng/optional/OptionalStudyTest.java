package com.zheng.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Optional测试，受Guava.Optional启发
 * Created by zhenglian on 2017/5/30.
 */
public class OptionalStudyTest {

    @Test
    public void testNull() {
        Optional<String> optional = Optional.ofNullable(null);
        System.out.println("First Name set? " + optional.isPresent());
        System.out.println("First Name: " + optional.orElseGet(()->"[none]"));
        System.out.println(optional.map(s->"Hey " + s).orElse("Hey Stranger"));
    }

    @Test
    public void testNotNull() {
        Optional<String> optional = Optional.ofNullable("Marry");
        System.out.println("First Name set? " + optional.isPresent());
        System.out.println("First Name: " + optional.orElseGet(()->"[none]"));
        System.out.println(optional.map(s->"Hey " + s).orElse("Hey Stranger"));
    }
    
    

}
