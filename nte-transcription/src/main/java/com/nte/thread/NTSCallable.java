package com.nte.thread;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nte.http.util.JSONUtil;
import com.nte.http.util.NTEHttpClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Created by litong on 2017/9/20.
 */
public class NTSCallable implements Callable<Map<String,String>>{

    private static NTEHttpClient client=null;

    //nte服务器的url
    private String nteUrl=null;
    //声音文件的url,推荐存放10个url
    private List<String> wavUrls=null;
    //url中的起始索引和终止索引
    private int start;
    private int end;

    public void setStart(int start) {
        this.start = start;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public NTSCallable(String nteUrl,List<String> wavUrls,int start,int end) {
        this.nteUrl = nteUrl;
        this.wavUrls = wavUrls;
        this.start=start;
        this.end=end;
    }

    /**
     * 将多个url提交到nte服务器,返回nte服务器转写之后的文本
     */
    @Override
    public Map<String,String> call() throws Exception {
        //请求的json字符
        String reqJson = JSONUtil.getJson(wavUrls, start, end);
        System.out.println(reqJson);
        //第一次响应的json字符
        String repJson1 = client.post(reqJson);
        System.out.println(repJson1);
        String uuid = repJson1.substring(14, 50);
        //第二次响应的json字符
        JSONObject repJson2=null;
        while (true) {
            //睡眠一秒
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //在new clent时指定过url,这里只需要指定RESTful
            String s = client.get("/status/" + uuid);
            repJson2 = JSON.parseObject(s);
            if (repJson2.getString("status").equals("TRANSCRIBED")) {
                System.out.println(repJson2);
                //在这里使用break可以跳出while循环
                break;
            }
        }
        //将音频和文本拼接成json返回
        Map<String,String> map=new HashMap<>();
        for(;start<end;start++){
            String text=repJson2.getJSONObject("channels").getJSONObject("channel" + (start + 1)).getString("text");
            map.put(wavUrls.get(start),text);
        }
        return new HashMap<>();
    }
}
