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
import java.time.Clock;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 函数式编程风格，极大的简化了集合类型的处理
 * stream操作被分解为中间操作和最终操作，
 * 中间操作直接返回一个新的stream,采用惰性处理的方式，不会理解对数据进行处理，而是在最终操作执行前进行处理的
 * 最终操作完成后当前stream就被认为已经被处理完成而不会再次被引用，如果需要再对结果进行计算，需要将结果封装到
 * 新的stream中进行处理
 * Created by zhenglian on 2017/5/30.
 */
public class MyStreamTest {
    private List<Task> tasks = Lists.newArrayList();
    
    private List<Integer> nums = Lists.newArrayList();
    
    
    @Before
    public void init() {
        Task task = new Task(Task.Status.OPEN, 25, "1");
        tasks.add(task);
        task = new Task(Task.Status.OPEN, 15, "2");
        tasks.add(task);
        task = new Task(Task.Status.CLOSED, 8, "3");
        tasks.add(task);
        
        nums.add(1);
        nums.add(3);
        nums.add(2);
        nums.add(4);
        nums.add(0);
        nums.add(0);
        nums.add(0);
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
        
        System.out.println("to map: ");
        Map<String, Integer> map = tasks.stream().collect(Collectors.toMap(task->task.getName(), task->task.getPoints()));
        System.out.println(map);
        
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
    
    @Test
    public void testSort() {
        nums.sort(Comparator.<Integer>comparingInt(Integer::valueOf).reversed());
        System.out.println(nums);
    }
    
    @Test
    public void testFilter() {
        List<Integer> result = nums.stream().filter(i -> i > 1).map(Integer::valueOf).sorted((a, b)->b.compareTo(a)).collect(Collectors.toList());
        System.out.println(result);
    }
    
    @Test
    public void testParallelStream() {
        List<Integer> result = nums.parallelStream().filter(i -> i > 1).sorted().collect(Collectors.toList());
        System.out.println(result);
    }
    
    @Test
    public void compareTwoStream() {
        Integer[] counts = new Integer[100];
        Arrays.parallelSetAll(counts, index -> ThreadLocalRandom.current().nextInt(100) + 1);
        List<Integer> list = Arrays.stream(counts).collect(Collectors.toList());
        long start = Clock.systemUTC().millis();
        list.stream().filter(i -> i > 1).sorted();
        System.out.println("time: " + (Clock.systemUTC().millis() - start) + " ms"); //2ms

        start = Clock.systemUTC().millis();
        list.parallelStream().filter(i -> i > 1).sorted();
        System.out.println("time: " + (Clock.systemUTC().millis() - start) + " ms"); //0ms
        
    }
    
    @Test
    public void testCount() {
        long count = nums.stream().filter(a -> a>1).count();
        System.out.println("count: " + count);
        
        List<Integer> result = nums.stream().filter(a->a>1).collect(Collectors.toList());
        result.stream().forEach(a->System.out.println(a));
    }
    
    /////////////////////////////////////////////////////////////////创建stream
    
    @Test
    public void createStream() {
        //1
        List<String> list = Stream.of("hello").collect(Collectors.toList());
        System.out.println(list);
        
        //2
        List<Integer> nums = Stream.of(1,2,3,4).collect(Collectors.toList());
        System.out.println(nums);
        
        //3,注意使用limit防止无限打印
        Stream.generate(()->ThreadLocalRandom.current().nextInt(10)+1).limit(10).forEach(a->System.out.println(a));
        
        
        //4,iterate 注意使用limit防止无限打印 result: [1,2,3,4,5,6,7,8,9,10]
        Stream.iterate(1, item->item+1).limit(10).forEach(a->System.out.println(a));
        
        //5,通过集合生成
        nums.stream().forEach(num->System.out.println(num));
    }
    
    /////////////////////////////////////////////////////////////////////转换stream
    
    
    @Test
    public void testDistinct() {
        List<Integer> result = nums.stream().distinct().collect(Collectors.toList());
        System.out.println(result);
    }
    
    @Test
    public void testFilter1() {
        nums.stream().filter(a->a>1).forEach(s->System.out.println(s));
    }

    /**
     * 注意使用Stream.collect()这才不会有问题
     * IntStream/LongStream.collect会报错，可以通过boxed()将其转换为Stream
     */
    @Test
    public void testMap1() {
        List<Long> result = nums.stream().map(num->Long.parseLong(num+"")).collect(Collectors.toList());
        System.out.println(result);
        
        result = nums.stream().mapToLong(a->Long.parseLong(a+"")).boxed().collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * collect(
     * supplier, 工厂函数，用来生成一个新的容器
     * accumular, 收集容器，将集合中的元素加入到中间容器中
     * combiner 用来把中间状态的多个容器结合成最后的结果容器
     * 当然还可以使用上面的简单方式
     * )语法
     */
    @Test
    public void testCollect() {
        List<Long> result = nums.stream().mapToLong(a->Long.parseLong(a+"")).collect(()->new ArrayList<Long>(), 
                (list, item)->list.add(item), (list1, list2) -> list1.addAll(list2));
        System.out.println(result);
    }
    
    @Test
    public void testFlatMap() {
        List<Integer> points = tasks.stream().mapToInt(Task::getPoints).boxed().collect(Collectors.toList());
        
        //合并collection,采用flatMap合并集合时不会new出新的集合，方法语句块返回的还是一个stream
        List<Integer> result = Stream.of(points, nums).flatMap(a->a.stream()).sorted().collect(Collectors.toList());
        System.out.println(result);
    }

    /**
     * 将存在多个元素的集合转变成一个元素的操作
     * 可以用于实现sum,max,min等操作
     * 
     */
    @Test
    public void testReduce() {
        // sum 
        int result = nums.stream().reduce(0, (sum, num) -> sum+num); // 0 是用来启动reduce的
        System.out.println("sum: " + result);
        
        
        // max
        int max = nums.stream().reduce(Integer.MIN_VALUE, Integer::max);
        System.out.println("max: " + max);
    }

    /**
     * peek会复制原集合中的元素产生新的集合，并针对每一个元素执行consumer操作
     */
    @Test
    public void testPeek() {
        nums.stream().peek(a->System.out.println(a));
        nums.stream().peek(System.out::print);
    }
    
    @Test
    public void testSkip() {
        nums.stream().skip(4).forEach(s->System.out.println(s));
    }

    /**
     * 查找集合中是否有匹配条件的元素存在，返回boolean类型结果
     * 可以通过allMatch,anyMatch,noneMatch方法
     */
    @Test
    public void testMatching() {
        boolean result = nums.stream().anyMatch(num->num>1);
        System.out.println(result);
        
        result = nums.stream().allMatch(num->num>2);
        System.out.println(result);
        
        result = nums.stream().noneMatch(num->num==0);
        System.out.println(result);
        
    }

    /**
     * 用于对stream集合中的元素进行检索操作
     * findFirst查找第一个匹配的元素
     * findAny返回其中任何一个元素
     * 
     */
    @Test
    public void testFinding() {
        Optional<Integer> result = nums.stream().filter(num->num>1).findFirst();
        System.out.println(result.orElse(-1));
        
        result = nums.stream().filter(num->num>1).findAny();
        System.out.println(result.orElse(-1));
    }
    
    @Test
    public void toMap() {
        Map<Integer, Task> map = tasks.stream().collect(Collectors.toMap(Task::getPoints, p->p));
        System.out.println(map);
    }
    
    
    
    
}
