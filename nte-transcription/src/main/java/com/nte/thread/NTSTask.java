package com.nte.thread;


import java.util.concurrent.RecursiveTask;

/**
 * Created by litong on 2017/9/20.
 *
 */
//编写一个可分解的任务
public class NTSTask extends RecursiveTask<String> {

    //urls中存放是url的列表
    private String[] urls;
    //start和end是起始索引和结束索引
    private int start;
    private int end;

    //在构造时进行赋值
    public NTSTask(String[] urls, int start, int end) {
        this.urls = urls;
        this.start = start;
        this.end = end;
    }

    //将一个大的任务进行分解
    @Override
    protected String compute() {
        if((end-start)<2){
            //处理任务
        }else{
            //分解任务
            int middle=(start+end)/2;
            NTSTask left = new NTSTask(urls, start, middle);
            NTSTask rigth = new NTSTask(urls, middle, end);
            //使用fork执行compute方法
            left.fork();
            rigth.fork();
        }
        return null;
    }
}
