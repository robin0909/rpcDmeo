package com.robin.rpc.container;

/**
 * web容器
 * Created by robin on 2016/10/8.
 */
public abstract class Container {

    public static  volatile  boolean isStart = false;

    public abstract void start();

    public static volatile Container container = null;
}
