package com.zheng.repeatableannotation;

/**
 * 这里使用重复注解，屏蔽Filters注解
 * Created by zhenglian on 2017/5/30.
 */
@Filter("filter1")
@Filter("filter2")
public interface Filtable {
}
