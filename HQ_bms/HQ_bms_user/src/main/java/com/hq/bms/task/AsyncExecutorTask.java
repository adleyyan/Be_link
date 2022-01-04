package com.hq.bms.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AsyncExecutorTask {


    @Async("taskExecutor")
    public void doTaskOne() throws Exception {
        System.err.println("任务一，当前线程：" + Thread.currentThread().getName());
    }

    @Async("taskExecutor")
    public void doTaskTwo() throws Exception {
        System.err.println("任务二，当前线程：" + Thread.currentThread().getName());
    }

    @Async("taskExecutor")
    public void doTaskThree() throws Exception {
        System.err.println("任务三，当前线程：" + Thread.currentThread().getName());
    }

}
