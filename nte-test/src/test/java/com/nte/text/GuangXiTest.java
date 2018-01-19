package com.nte.text;

import com.hg.utils.NveJaveUtil;
import com.nte.http.util.SingleThreadNTEClient;
import com.nte.pojo.TapeInfo;
import it.sauronsoftware.jave.EncoderException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by litong on 2018/1/18.
 */
public class GuangXiTest {
    /**
     * 广西电网语音测试
     */
    @Test
    public void test1(){
        String nte_url="http://192.168.28.182:8100";
        List<String> wavs=new ArrayList<>();
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_085357.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_144808.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_155906.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022001_20180110_103338.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022001_20180110_112607.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022002_20180110_085518.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022003_20180110_112657.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022007_20180110_152602.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022014_20180110_101945.wav");
        wavs.add("http://192.168.28.100:8080/tape/t92022025_20180110_110053.wav");
        SingleThreadNTEClient client = SingleThreadNTEClient.getClient(nte_url);
        Map<String, TapeInfo> map = client.vtsDiarize(wavs);

        for (Map.Entry<String, TapeInfo> m : map.entrySet()) {
            System.out.println(m.getKey()+":"+m.getValue());

        }
    }
    /**
     * 进行声音转接,将mp3转换成wav
     */
    @Test
    public void test2(){
        List<String> wavs=new ArrayList<>();
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022000_20180110_085357.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022000_20180110_144808.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022000_20180110_155906.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022001_20180110_103338.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022001_20180110_112607.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022002_20180110_085518.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022003_20180110_112657.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022007_20180110_152602.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022014_20180110_101945.mp3");
        wavs.add("D:\\Program Files\\apache-tomcat-7-8080\\webapps\\tape\\t92022025_20180110_110053.mp3");

        try {
            NveJaveUtil.formatMp3ToWav(wavs);
        } catch (EncoderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        String nte_url="http://192.168.28.182:8100";
        String url="http://192.168.28.100:8080/tape/t92022025_20180110_110053.wav";
        SingleThreadNTEClient client = SingleThreadNTEClient.getClient(nte_url);
        long l = System.currentTimeMillis();
        Map<String, TapeInfo> map = client.vtsDiarize(url);
        long l1 = System.currentTimeMillis();
        System.out.println((l1-l)/1000);


    }

}
