package com.test.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by litong on 2017/12/8.
 */
public class Test1 {
    public static void main(String[] args) {
        HttpClientBuilder hcb = HttpClientBuilder.create();
        CloseableHttpClient chc = hcb.build();


    }
}
