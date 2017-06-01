package com.zheng.datetime;

import org.junit.Before;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * 采用joda-time语法
 * Created by Administrator on 2017/5/31.
 */
public class MyDateTimeTest {

    /**
     * 采用Clock对象，带时区
     */
    private Clock clock;
    
    @Before
    public void init() {
        clock = Clock.systemUTC();
    }
    
    @Test
    public void testSystemTime() {
        System.out.println(clock.millis());
        System.out.println(clock.instant());
    }

    /**
     * 没有时区概念
     */
    @Test
    public void testDate() {
        LocalDate date = LocalDate.now();
        System.out.println(date.getYear() + "," + date.getMonth().getValue() + "," 
                + date.getDayOfMonth() + "," + date.getDayOfWeek().getValue());

        //从clock中获取
        LocalDate fromClockDate = LocalDate.now(clock);
        System.out.println(fromClockDate);
    }

    /**
     * 无时区概念
     */
    @Test
    public void testTime() {
        LocalTime time = LocalTime.now();
        System.out.println(time.getHour() + "," + time.getMinute() + "," + time.getSecond()
            + "," + time.getNano());
        System.out.println(clock.millis());
        
        LocalTime fromClockTime = LocalTime.now(clock);
        System.out.println(fromClockTime);
        
    }
    
    @Test
    public void testLocalDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();

        LocalDateTime instance = LocalDateTime.of(2017,4,4,14,30);
        System.out.println(instance);
        
        System.out.println("format date: ");
        
        String result = instance.format(DateTimeFormatter.BASIC_ISO_DATE);
        System.out.println(result); // yyyyMMdd
        
        result = instance.format(DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(result); // yyyy-MM-ddTHH:mm:ss
        
        result = instance.format(DateTimeFormatter.ISO_LOCAL_DATE); // yyyy-MM-dd
        System.out.println(result);
        
        result = instance.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);// yyyy-MM-ddTHH:mm:ss
        System.out.println(result);

        String str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(str);
    }
    
    @Test
    public void testFormat() {
        LocalDateTime dateTime = LocalDateTime.now();
        String str = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(str);
    }

    /**
     * 指定时区时间
     */
    @Test
    public void testZoneDateTime() {
       ZonedDateTime dateTime = ZonedDateTime.now();
       System.out.println(dateTime);
       
       dateTime = ZonedDateTime.now(clock);
       System.out.println(dateTime);
       
       dateTime = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
       System.out.println(dateTime);
       
       dateTime = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
       System.out.println(dateTime);

       DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
       String result = ZonedDateTime.now().format(FORMATTER);
       System.out.println(result);
    }
    
    @Test
    public void testDuration() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println("now:");
        System.out.println(now);
        
        LocalDateTime future = now.plusDays(3);
        System.out.println(future);
        
        Duration duration = Duration.between(now, future);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
    }

    /**
     * 日期之间的计算
     */
    @Test
    public void testCalc() {
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime);
        LocalDateTime result = dateTime.plusDays(3).plusMinutes(30).plusSeconds(30);
        System.out.println(result);
        
        result = result.minusDays(3).minusMinutes(30).minusSeconds(30);
        System.out.println(result);
    }
    
    
}
