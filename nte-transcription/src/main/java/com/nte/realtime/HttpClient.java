package com.nte.realtime;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by litong on 2017/12/31.
 */
public class HttpClient {
    private static Map<String,CloseableHttpClient> httpClients=new HashMap<>();
    //从连接池中获取连接超时,连接超时,读取超时
    private static RequestConfig requestConfig = RequestConfig.custom().
            setConnectionRequestTimeout(10000).setConnectTimeout(10000).setSocketTimeout(10000).build();
    private final static Object syncLock=new Object();

    private static CloseableHttpClient getHttpClient(String url){
        // http://www.baidu.com
        // [0] http
        // [1]
        // [2] www.baidu.com
        String hostname=url.split("/")[2];
        return null;
    }
}
