package com.zheng.repeatableannotation;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class RepeatableAnnotation {
    
    @Test
    public void testFilter() {
        Filter[] filters = Filtable.class.getAnnotationsByType(Filter.class);
        Arrays.asList(filters).forEach(filter -> System.out.println(filter.value()));
    }
    
    @Test
    public void testFilters() {
        Filters filter = Filtable.class.getAnnotation(Filters.class);
        System.out.println(filter);
        Filter[] filters = filter.value();
        System.out.println(filters.length);
        Arrays.asList(filters).forEach(f -> System.out.println(f.value()));
    }
    
}
