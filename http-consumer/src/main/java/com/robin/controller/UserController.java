package com.robin.controller;

import com.robin.bean.User;
import com.robin.interf.UserService;
import com.robin.rpc.invoke.ConsumerConfig;
import com.robin.rpc.proxy.ConsumerProxyFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.*;

import javax.annotation.Resource;

/**
 * Created by robin on 2016/10/8.
 */
public class UserController {

//    @Resource
//    private static UserService userService;

    public static void main(String[] args) throws InterruptedException, ClassNotFoundException {
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-consumer.xml");
//        context.start();
//        Thread.sleep(1000);
//        CountDownLatch countDownLatch = new CountDownLatch(1);
//        if(userService != null) {
//            countDownLatch.countDown();
//            System.out.println("userService获取失败！");
//            return;
//        }
//        countDownLatch.await();

        ConsumerProxyFactory factory = new ConsumerProxyFactory();
        factory.setClazz("com.robin.interf.UserService");

        ConsumerConfig config = new ConsumerConfig();
        config.setUrl("http://127.0.0.1:8080/invoke");

        factory.setConsumerConfig(config);

        UserService  userService = (UserService) factory.create();

        User user = userService.getUser(1);
        System.out.println(user);
    }
}
