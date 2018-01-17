package com.nte.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by litong on 2017/9/20.
 */
public class ThreadPool {
    private static ExecutorService pool=null;
    //初始换线程池
    static {
        //得到CPU的核心数
        int i = Runtime.getRuntime().availableProcessors();
        pool = Executors.newFixedThreadPool(i);
    }
    /**
     * 返回创建的线程池
     */
    public static ExecutorService getPool(){
        return pool;
    }
}
