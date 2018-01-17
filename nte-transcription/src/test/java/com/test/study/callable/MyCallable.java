package com.test.study.callable;

import java.util.concurrent.Callable;

/**
 * Created by zhouyangying on 2017/9/20.
 */
public class MyCallable implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "i am litong";
    }
}
