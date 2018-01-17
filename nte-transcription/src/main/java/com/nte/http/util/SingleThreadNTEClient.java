package com.nte.http.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.nte.pojo.Session;
import com.nte.pojo.TapeInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by litong on 2017/9/21.
 * 这是一个单例模式的客户端
 */
public class SingleThreadNTEClient {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private NTEHttpClient httpClient;
    private static SingleThreadNTEClient client;

    //构造器私有
    private SingleThreadNTEClient(String nteUrl) {
        httpClient = new NTEHttpClient(URLUtil.valid(nteUrl));
    }

    public static SingleThreadNTEClient getClient(String nteUrl) {
        if (client != null) {
            return client;
        } else {
            client = new SingleThreadNTEClient(nteUrl);
            return client;
        }
    }

    /**
     * 一次提交连个音频的url到服务器
     */
    public Map<String, String> vts(String wav1, String wav2) {
        List<String> list = getList(wav1, wav2);
        String json = JSONUtil.getJson(list, 0, list.size());
        //第一次响应的json字符

        JSONObject repJson2 = common(json);
        //将音频和文本拼接成json返回
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < list.size(); i++) {
            JSONArray transcript = getTranscript("channel" + (i + 1), repJson2);
            String text = transcript.getJSONObject(0).getString("text");
            map.put(list.get(i), text);
        }
        return map;
    }

    /**
     * 坐席和客户向分离的转写
     */
    public Map<String, TapeInfo> vtsDiarize(String wav1) {
        Map<String, TapeInfo> map = new HashMap<>();
        List<String> list = getList(wav1, null);
        String json = JSONUtil.getDiarizeJson(list, 0, list.size());
        logger.info("发送json到nte:" + json);
        JSONObject repJson2 = common(json);
        logger.info("第二次响应的信息:" + repJson2.toString());


        for (int i = 0; i < list.size(); i++) {
            TapeInfo info = new TapeInfo();
            //得到声音总长度
            info.setAudio_length(repJson2.getJSONObject("channels").
                    getJSONObject("channel" + (i + 1)).getJSONObject("statistics").
                    getBigDecimal("audio_length"));

            //转换时有多个对话
            JSONArray transcript = getTranscript("channel" + (i + 1), repJson2);
            logger.info("返回的transcript信息" + transcript.toString());
            List<Session> sessions = new ArrayList<Session>();
            for (int j = 0; j < transcript.size(); j++) {
                Session session = JSON.parseObject(transcript.getString(j), Session.class);
                sessions.add(session);
            }
            info.setSessions(sessions);
            logger.info("录音文件名:" + list.get(i).toString());
            logger.info("对应的录音信息:"+info.toString());
            map.put(list.get(i),info);
        }
        return map;
    }

    /**
     * 传入list进行转换
     */
    public Map<String, TapeInfo> vtsDiarize(List<String> wavs) {
        if(wavs.size()<1){
            logger.info("list中不是存在文件,本次转换返回null");
            return null;
        }

        Map<String,TapeInfo> result=new HashMap<>();
        for (String wav : wavs) {
            //如果文件名相同,就不进行转换
            if(result.containsKey(wav)){
                logger.info("重复的文件名,不进行转换:"+wav);
            }else{

                Map<String, TapeInfo> subMap = vtsDiarize(wav);

                for (Map.Entry<String, TapeInfo> entry : subMap.entrySet()) {
                    result.put(entry.getKey(),entry.getValue());
                }
            }
        }
        return result;
    }


    /**
     * 取出Transcript部分,
     * channelStr, channel的名称
     * JSONObject第二次返回的json对象,
     */
    private JSONArray getTranscript(String channelStr, JSONObject repJson2) {
        JSONObject channels = repJson2.getJSONObject("channels");
        JSONObject channel = channels.getJSONObject(channelStr);
        JSONArray transcript = channel.getJSONArray("transcript");
        return transcript;
    }

    /*
     *抽取的公共方法,将json发送到nte,得到返回值
     */
    public JSONObject common(String json) {
        //第一次响应的json字符
        String uuid = httpClient.post(json);
        logger.info("第一次请求的响应:" + uuid);
        uuid = uuid.substring(14, 50);
        //第二次响应的json字符
        JSONObject repJson2 = null;
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i=1;
        String s = httpClient.get("status/" + uuid);
        logger.info("根据uuid第"+i+"次请求:"+s);
        repJson2 = JSON.parseObject(s);

        while (!repJson2.getString("status").equals("TRANSCRIBED")) {
            //睡眠3秒
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //在new clent时指定过url,这里只需要指定RESTful
            s = httpClient.get("status/" + uuid);
            i++;
            logger.info("根据uuid第"+i+"次请求:"+s);
            repJson2 = JSON.parseObject(s);
        }
        return repJson2;
    }

    /**
     * 查询引擎状态
     */
    public boolean getPing() {
        String ping = httpClient.get("ping");
        JSONObject j = JSON.parseObject(ping);
        logger.info("返回信息:" + j.toString());
        String status = j.getString("status");
        if (status.equals("WORKING")) {
            return true;
        } else {
            return false;
        }
    }

    /*
     *查询语言包信息
     */
    public String getLanguage() {
        return httpClient.get("langpackdetails");
    }

    /**
     * 查询licensing
     */
    public String getLicensing() {
        return httpClient.get("licensedetails?threshold=0-100");
    }

    //拼接list
    private List getList(String wav1, String wav2) {
        List<String> list = new ArrayList<String>();
        if (wav1 != null) {
            list.add(wav1);
        }
        if (wav2 != null) {
            list.add(wav2);
        }
        return list;
    }
}
