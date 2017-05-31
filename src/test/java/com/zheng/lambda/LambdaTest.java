package com.zheng.lambda;

import com.zheng.lambda.Lambda;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class LambdaTest {
    
    private Lambda lambda;
    
    @Before
    public void init() {
        lambda = new Lambda();
    }
    
    
    @Test
    public void test() {
        lambda.list();
    }
    
}
