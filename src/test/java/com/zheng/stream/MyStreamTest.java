package com.zheng.stream;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class MyStreamTest {
    private List<Task> tasks = Lists.newArrayList();
    
    @Before
    public void init() {
        Task task = new Task(Task.Status.OPEN, 25);
        tasks.add(task);
        task = new Task(Task.Status.OPEN, 15);
        tasks.add(task);
        task = new Task(Task.Status.CLOSED, 8);
        tasks.add(task);
    }


    /**
     * 统计状态为open的任务总分数
     */
    @Test
    public void testOpenPointSum() {
        int sum = tasks.stream().filter(task -> task.getStatus() == Task.Status.OPEN)
                .mapToInt(Task::getPoints).sum();
        System.out.println(sum);
    }

    /**
     * map用于Object, 通用方法
     */
    @Test
    public void testMap() {
        int sum = tasks.stream().map(Task::getPoints).mapToInt(obj->{return (Integer)obj;}).sum();
        System.out.println(sum);
        //不推荐上面的方法,直接使用mapToInt即可，这里只是为了做例子
    }
    

    /**
     * 并行处理
     * 需要说明的是被处理的对象之间是没有关系可独立运行业务逻辑的数据
     */
    @Test
    public void testParallel() {
       int result = tasks.stream()
               .parallel()
               .filter(task -> task.getStatus() == Task.Status.OPEN)
               .mapToInt(Task::getPoints)
               .reduce(0, Integer::sum);
        System.out.println(result);
    }

    /**
     * 分组集合
     */
    @Test
    public void testGroupBy() {
        Map<Task.Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(task->task.getStatus()));
        System.out.println(map);
    }

    /**
     * 计算各个任务百分比
     */
    @Test
    public void example() {
        int totalPoints = tasks.stream().mapToInt(Task::getPoints).sum();
        System.out.println(totalPoints);
        Collection<String> result = tasks.stream()
                .mapToInt(Task::getPoints)
                .mapToDouble(point -> {
                    BigDecimal one = new BigDecimal(point);
                    BigDecimal two = new BigDecimal(totalPoints);
                    BigDecimal three = one.divide(two, 4, RoundingMode.HALF_EVEN);
                    BigDecimal four = three.multiply(new BigDecimal(100));
                    return four.doubleValue();
                })
                .mapToObj(percentage -> percentage + "%")
                .collect(Collectors.toList());
        System.out.println(result);
    }
    
    @Test
    public void testReadFile() {
        Path path = Paths.get("D:\\学习笔记\\android 安全手机项目知识点汇总.txt");
        try (Stream<String> lines = Files.lines(path, StandardCharsets.UTF_8)) {
            lines.onClose(()->System.out.println("Done!")).forEach(line->System.out.println(line));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
}
