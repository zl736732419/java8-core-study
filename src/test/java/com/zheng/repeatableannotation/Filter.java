package com.zheng.repeatableannotation;

import java.lang.annotation.*;

/**
 * Created by zhenglian on 2017/5/30.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(Filters.class)
public @interface Filter {
    String value();
}
