package com.nte.http.util;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * Created by litong on 2017/9/21.
 */
public class JSONUtil {
    //设置转写模式
    private static String operating_mode="fast";
    public static void setOperating_modeIsFast(){
        operating_mode="fast";
    }

    public static void setOperating_modeIsAccurate(){
        operating_mode="accurate";
    }
    public static void setOperating_modeIsWarp(){
        operating_mode="wrap";
    }
    //添加关键词
    //关键词转换后的json对象
    private static JSONObject wordsJsonObject = null;
    //map转成object方法
    public static JSONObject setWords(Map<String,List<String>> words){
        JSONObject classes = new JSONObject();
        for (Map.Entry<String, List<String>> e : words.entrySet()) {
            String defaultValue = e.getValue().toString();
            JSONObject defaultJson = new JSONObject();
            defaultJson.put("default", defaultValue);
            classes.put(e.getKey(),defaultJson);
        }
        wordsJsonObject=new JSONObject();
        wordsJsonObject.put("classes",classes);
        return wordsJsonObject;
    }




    /*
     *传入list集合,返回josn的请求串[start,end}
     */
    public static String getJson(List<String> wavUrls, int start, int end) {
        JSONObject j = getModel();
        JSONObject channels = getChannels(wavUrls, start, end);
        j.put("channels", channels);
        //请求的json字符
        String reqJson = j.toString();
        return reqJson;
    }

    /**
     *返回转写模式和转写语言包
     */
    private static JSONObject getModel(){
        JSONObject j = new JSONObject();
        j.put("operating_mode", operating_mode);

        JSONObject model = new JSONObject();
        model.put("name", "zho-chn");
        if(wordsJsonObject!=null){
            model.put("addwords", wordsJsonObject);
        }
        j.put("model", model);

        return j;
    }

    /**
     * 返回Channels,包含多个声音文件
     */
    private static JSONObject getChannels(List<String> wavUrls, int start, int end) {
        //声音数据
        JSONObject channels = new JSONObject();
        for (; start < end; start++) {
            //单个声音数据
            JSONObject channel1 = getChannel(wavUrls.get(start));
            //如果k值相同,加入新的会替换掉旧的,
            channels.put("channel" + (start + 1), channel1);
        }
        return channels;
    }

    /**
     * 返回channel,包含单个声音文件,基本格式
     */
    private static JSONObject getChannel(String url) {
        JSONObject channel1 = new JSONObject();
        channel1.put("url", url);
        channel1.put("format", "audio/wave");
        channel1.put("result_format", "transcript");
        channel1.put("result_version", 1);
        return channel1;
    }

    /**
     * 语音转文字,分离坐席和用户
     */
    public static String getDiarizeJson(List<String> list,int start,int end) {
        JSONObject j = getModel();
        JSONObject channels = new JSONObject();
        for(int i=start;i<end;i++){
            JSONObject channel = getChannel(list.get(i));
            channel.put("diarize", true);
            channel.put("num_speakers", 2);
            channels.put("channel" + (i + 1), channel);
        }
        j.put("channels", channels);
        return j.toString();
    }
}
