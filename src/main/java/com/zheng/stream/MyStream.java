package com.zheng.stream;

/**
 * 函数式编程风格，极大的简化了集合类型的处理
 * stream操作被分解为中间操作和最终操作，
 * 中间操作直接返回一个新的stream,采用惰性处理的方式，不会理解对数据进行处理，而是在最终操作执行前进行处理的
 * 最终操作完成后当前stream就被认为已经被处理完成而不会再次被引用，如果需要再对结果进行计算，需要将结果封装到
 * 新的stream中进行处理
 * by zhenglian on 2017/5/30.
 */
public class MyStream {
}
