package com.zheng.base64;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Base64工具
 * Created by zhenglian on 2017/6/1.
 */
public class Base64Test {
    
    @Test
    public void testCreateCode() {
        String str = "Base64 in java8";
        //encode
        String result = Base64.getEncoder().encodeToString(str.getBytes(StandardCharsets.UTF_8));
        System.out.println(result);
        
        //decode
        String decode = new String(Base64.getDecoder().decode(result), StandardCharsets.UTF_8);
        System.out.println(decode);
        
    }
    
    @Test
    public void testUrl() {
        String url = "http://www.baidu.com";
        String encode = Base64.getUrlEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode);
        
        String encode2 = Base64.getEncoder().encodeToString(url.getBytes(StandardCharsets.UTF_8));
        System.out.println(encode2);
        
        String decode = new String(Base64.getUrlDecoder().decode(encode), StandardCharsets.UTF_8);
        System.out.println(decode);
        
    }
    
    
    
}
