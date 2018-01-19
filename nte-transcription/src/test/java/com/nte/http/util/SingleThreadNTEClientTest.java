package com.nte.http.util;

import com.nte.pojo.TapeInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by litong on 2018/1/11.
 */
public class SingleThreadNTEClientTest {

    //@Test
    public void vts() throws Exception {
        String server_url="http://192.168.28.182:8100";
        SingleThreadNTEClient client = SingleThreadNTEClient.getClient(server_url);
        String wavUrl="http://111.202.106.147:1028/QCSystem/Tape/2018/01/11/out-18938403466-2036-20180111-183050-1515666650.9277.wav";
        Map<String, TapeInfo> map = client.vtsDiarize(wavUrl);
        for (Map.Entry<String, TapeInfo> m : map.entrySet()) {
            System.out.println(m.getKey());
            System.out.println(m.getValue());
        }


    }

    /**
     * 完成广西电网项目测试
     */
    //@Test
    public void guangxiTest(){
        List<String> wavs=new ArrayList<>();
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_085357.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022001_20180110_103338.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022003_20180110_112657.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022025_20180110_110053.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_144808.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022001_20180110_112607.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022007_20180110_152602.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022000_20180110_155906.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022002_20180110_085518.mp3");
        wavs.add("http://192.168.28.100:8080/tape/t92022014_20180110_101945.mp3");
        String server_url="http://192.168.28.182:8100";
        SingleThreadNTEClient client = SingleThreadNTEClient.getClient(server_url);
        Map<String, TapeInfo> wavMap = client.vtsDiarize(wavs);
        System.out.println("开始遍历信息");
        for (Map.Entry<String, TapeInfo> e : wavMap.entrySet()) {
            System.out.println(e.getKey()+":"+e.getValue());
        }



    }
}