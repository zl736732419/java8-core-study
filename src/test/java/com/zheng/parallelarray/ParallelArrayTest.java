package com.zheng.parallelarray;

import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * 并行数组
 * Created by Administrator on 2017/6/1.
 */
public class ParallelArrayTest {
    
    private int[] arr = new int[1000];
    
    @Before
    public void init() {
        Arrays.parallelSetAll(arr, index -> ThreadLocalRandom.current().nextInt(1000));
        
    }
    
    @Test
    public void printLimit10() {
        Arrays.stream(arr).limit(10).forEach(i -> System.out.println(i));
        
        System.out.println("sort 10 num: ");
        long start = Clock.systemUTC().millis();
        Arrays.stream(arr).limit(100).sorted().forEach(i -> System.out.println(i));
        System.out.println("time: " + (Clock.systemUTC().millis() - start) + "ms"); //7ms
        
        start = Clock.systemUTC().millis();
        Arrays.parallelSort(arr);
        Arrays.stream(arr).limit(10).forEach(i -> System.out.println(i));
        System.out.println("time: " + (Clock.systemUTC().millis() - start) + "ms"); //2ms
        
        arr = new int[5];
        Arrays.parallelSetAll(arr, index->ThreadLocalRandom.current().nextInt(5)+1);
        
        Arrays.stream(arr).mapToObj(num->(Integer)num).collect(Collectors.toList());
        List<Integer> list = Arrays.stream(arr).limit(5).boxed().collect(Collectors.toList());
        System.out.println(list);
        
        Arrays.parallelPrefix(list.stream().toArray(), (left, right) -> (Integer)left * (Integer)right);
        list = Arrays.stream(arr).limit(5).boxed().collect(Collectors.toList());
        System.out.println(list);
    }
    
}
