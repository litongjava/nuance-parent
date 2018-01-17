package com.nte;

import com.nte.http.util.NTEClient;
import com.nte.http.util.NTEHttpClient;
import com.nte.http.util.SingleThreadNTEClient;
import com.nte.pojo.TapeInfo;

import javax.swing.text.html.parser.Entity;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhouyangying on 2017/11/29.
 */
public class Main {
    public static void main(String[] args) {
        String serverUrl="http://192.168.28.182:8100";
        //String warUrl="http://47.92.106.197:8080/luyin/shengyin/out-13003528669-2037-20170911-183640-1505126200.1488772.wav";
        String wavurl="http://192.168.28.201:8080/QCSystem/Tape/2018/01/15/out-13887994287-2036-20180115-175215-1516009935.39749.wav";
        SingleThreadNTEClient client = SingleThreadNTEClient.getClient(serverUrl);
        
        //Map<String, String> vts = client.vts(warUrl, null);
        long startT = System.currentTimeMillis();
        Map<String, TapeInfo> vts = client.vtsDiarize(wavurl);
        long endT = System.currentTimeMillis();
        long executeT = (endT - startT) / 1000;
        System.out.println(executeT);

        System.out.println(vts);

    }
}
