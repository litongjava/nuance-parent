package com.test.study.se;

import org.junit.Test;

/**
 * Created by litong on 2017/12/13.
 */
public class TestMod {
    public static void main(String[] args) {
        float f=10.1F;
        int i=2;
        double ceil = Math.ceil(f);
        int ceilI=(int)ceil;
        System.out.println(ceilI);
        double mod=ceil%i;
        System.out.println(mod);
    }

    @Test
    public void test1(){
        float f=2.0F;
        System.out.println(f*2);
    }

    @Test
    public void test2(){
        //精度损失问题
        int length=8978732;
        int head=44;
        float t=561.168f;
        float v = (length - head) / t;
        double ceil = Math.ceil(v);
        System.out.println(ceil);
        int byteRate=(int)ceil;
        System.out.println(v);
        System.out.println(byteRate);


    }
}
