package com.test.study.callable;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Created by zhouyangying on 2017/9/20.
 */
public class MyTest {
    @Test
    public void test1(){
        FutureTask<String> task = new FutureTask<String>(new MyCallable());
        Thread thread = new Thread(task);
        thread.start();
        //一个线程执行完,会自动关闭
        //thread.stop();
        try {
            String s = task.get();
            System.out.println(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            final int index = i;

            try {
                Thread.sleep(index * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            cachedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                }
            });
        }
    }

    @Test
    public void test3(){
        //得到CPU的核心数
        int i = Runtime.getRuntime().availableProcessors();
        System.out.println(i);
    }

    @Test
    public void test4(){

    }

}
