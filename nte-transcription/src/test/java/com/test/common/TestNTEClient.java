package com.test.common;


import com.nte.websocket.NteClient;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by litong on 2017/9/20.
 */
public class TestNTEClient {

    /**
     * 测试webscoket 是否可以连接到服务器
     */
    @Test
    public void test1() throws IOException {
        String nte_url="ws://192.168.28.182:8100";
        //加入适配器
        NteClient.initClient(nte_url);
    }

}
