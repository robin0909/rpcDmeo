package com.robin.launch;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.concurrent.*;
/**
 * Created by robin on 2016/10/8.
 */
public class Launcher {

    public static void main(String[] args) throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:spring-context.xml");
        context.start();
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        countDownLatch.await();
    }
}
