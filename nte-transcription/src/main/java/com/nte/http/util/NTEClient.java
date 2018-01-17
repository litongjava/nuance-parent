package com.nte.http.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nte.thread.NTSCallable;
import com.nte.thread.ThreadPool;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * Created by zhouyangying on 2017/9/19.
 */
public class NTEClient {
    //nte服务器的地址和端口
    private static String nteUrl = null;
    private static NTEHttpClient client = null;
    //线程池对象
    private static ExecutorService pool = ThreadPool.getPool();
    //请求json格式
    private static String reqJson = null;
    //第一次请求返回的json
    private static String repJson1 = null;
    //第二次请求返回的json
    private static String repJson2 = null;

    public NTEClient(String url) {
        this.nteUrl = URLUtil.valid(url);
        client = new NTEHttpClient(url);
    }

    //返回请求的json格式
    public static String getReqJson() {
        return reqJson;
    }

    /**
     * 语音转文字,指定语音文件的url
     *
     * @Return json字符串, uuid;
     */
    public static Map<String, String> vts(List<String> wavUrl, int start, int end) {
        //map<音频文件名,对应的文本>
        Map<String, String> map = null;
        if (wavUrl != null) {
            for (int i=start; i < end; i++) {
                if (!wavUrl.get(i).startsWith("http://")) {
                    wavUrl.set(i, "http://" + wavUrl.get(i));
                }
            }
        }
        for (int i=start;i < end; i += 2) {
            //必须每次都实例化callback
            NTSCallable nteCall = new NTSCallable(nteUrl, wavUrl, 0, 0);
            //默认情况nte接受两个url
            if (i >=end-1) {
                nteCall.setStart(i);
                nteCall.setEnd(end);
            } else {
                nteCall.setStart(i);
                nteCall.setEnd(i + 2);
            }
            Future<Map<String, String>> submit = pool.submit(nteCall);
            while (true){
                if(!submit.isDone()){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    try {
                        Map<String, String> map1 = submit.get();
                        for (Map.Entry<String, String> e : map1.entrySet()) {
                            map.put(e.getKey(), e.getValue());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //返回map结果
        return map;
    }
}

