package com.test.httpclient;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by litong on 2017/12/9.
 */
public class HttpClientTest {
    public static void main(String[] args) {
        String url="www.baidu.com";
        String user_agent = "Mozilla/5.0";
        CloseableHttpClient hc = HttpClients.createDefault();
        HttpGet hg = new HttpGet(url);
        hg.addHeader("user-agent", user_agent);
        CloseableHttpResponse hr = null;
        try {
            hr = hc.execute(hg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取状态吗
        int statusCode = hr.getStatusLine().getStatusCode();
        System.out.println("statusCode=" + statusCode);
        //获取返回的内容
        InputStream content = null;
        try {
            content = hr.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader br = null;
        if (content != null) {
            br = new BufferedReader(new InputStreamReader(content));
        }

        StringBuffer sb = new StringBuffer();
        String temp = null;
        try {
            while ((temp = br.readLine()) != null) {
                sb.append(temp);
            }
            String s = sb.toString();
            System.out.println(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                hc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
