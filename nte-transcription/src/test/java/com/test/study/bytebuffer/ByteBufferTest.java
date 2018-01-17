package com.test.study.bytebuffer;

import java.nio.ByteBuffer;

/**
 * Created by litong on 2017/12/13.
 */
public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer bf = ByteBuffer.allocate(4);
        bf.putInt(4*8);
        byte[] array = bf.array();
        for (byte b : array) {
            System.out.println(b);
        }
    }
}
