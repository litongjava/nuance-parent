package com.hg.utils;

import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by litong on 2018/1/17.
 */
public class WavUtilTest {
    //@Test
    public void testPrperty() {
        String property = System.getProperty("java.io.tmpdir");
        System.out.println(property);
        //C:\Users\ZHOUYA~1\AppData\Local\Temp\
    }

    //@Test
    public void testStr() {
        String str = "D:\\\\Program Files\\apache-tomcat-7-8000\\webapps\\tape\\t92022000_20180110_085357.mp3";
        int i0 = str.lastIndexOf("\\");
        String subStr = str.substring(0, i0);
        System.out.println(subStr);
//        int i1 = str.lastIndexOf("\\\\");
//        int i2 = str.lastIndexOf("/");
//        System.out.println(i0+":"+i1+":"+i2);
    }

    //@Test
    public void testFile(){
        File file = new File("D:\\Program Files\\apache-tomcat-7-8000\\webapps\\tape\\t92022000_20180110_085357.wav");
        System.out.println(file.getAbsoluteFile());
        String absPath = file.getAbsolutePath();
        System.out.println(absPath);
        File file1 = new File(absPath);
        System.out.println(file1.getAbsoluteFile());
    }

    //@Test
    public void test3(){
        File file = new File("D:\\Program Files\\apache-tomcat-7-8000\\webapps\\tape\\t92022000_20180110_085357.mp3");
        System.out.println(file);
        System.out.println(file.getAbsoluteFile());
    }

    //@Test
    public void test5() throws EncoderException {
        formatToWav("D:\\Program Files\\apache-tomcat-7-8000\\webapps\\tape\\t92022000_20180110_085357.mp3");
    }

    public void test4(String filename){
        File file = new File(filename);
        System.out.println(file);
        System.out.println(file.getAbsoluteFile());
    }

    public static String formatToWav(String filename) throws IllegalArgumentException, InputFormatException, EncoderException {
        File file = new File(filename);
        System.out.println(file);
        System.out.println(file.getAbsoluteFile());
        return null;
    }

}