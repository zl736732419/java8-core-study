package com.zheng.stream;

/**
 * Created by zhenglian on 2017/5/30.
 */
public class Task {
    
    public enum Status {
        OPEN, CLOSED;
    }
    
    private Status status;
    private Integer points;

    public Task(Status status, Integer points) {
        this.status = status;
        this.points = points;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", status, points);
    }
}
