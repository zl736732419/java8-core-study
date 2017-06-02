package com.zheng.lambda;

import com.google.common.collect.Lists;

import java.util.Collections;
import java.util.List;

/**
 * 适用于接口中定义的方法只有一个的情况
 * Created by zhenglian on 2017/5/30.
 */
public class Lambda {
    
    private List<Integer> nums = Lists.newArrayList(1,2,3,4,5,6);
    
    public void list() {
        //无返回结果值
        nums.forEach((Integer a) -> System.out.println(a));
       

        Collections.shuffle(nums);
        System.out.println("乱序后的list: ");
        nums.forEach(a -> {
            System.out.println(a);
        });
        
        //有返回结果值
        nums.sort((a, b) -> a.compareTo(b));
        System.out.println("排序后的值：");
        nums.forEach(a -> {
            System.out.println(a);
        });
        
        
        
        
    }
    
}
