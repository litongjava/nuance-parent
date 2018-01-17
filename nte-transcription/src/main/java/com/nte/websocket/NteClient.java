package com.nte.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketFactory;
import com.nte.pojo.TapeInfo;

import java.io.IOException;
import java.util.Map;

/**
 * Created by litong on 2018/1/11.
 * nte转写的客户端
 */
public class NteClient {
    private static String nte_url = null;
    private static WebSocketFactory factory = new WebSocketFactory();
    private static WebSocket client;

    //private construct
    private NteClient() {
    }

    /**
     * init client
     *
     * @param nte_url
     * @throws IOException
     */
    public static void initClient(String nte_url) throws IOException {
        if (client == null) {
            client = factory.createSocket(nte_url);
            client.addListener( new NteAdapter());
        }
    }

    /**
     * 话 着 分离 转写 一次可以转连个文件
     *
     * @param wav1
     * @return
     */
    public Map<String, TapeInfo> vtsDiarize(String wav1, String wav2) {
        return null;
    }




    //send json to server

    // close client


}
