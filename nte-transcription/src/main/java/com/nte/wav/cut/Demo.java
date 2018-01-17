package com.nte.wav.cut;

import java.io.File;

/**
 * Created by litong on 2017/12/13.
 */
public class Demo {
    public static void main(String[] args) {
        //1.指定文件夹
        String path="E:\\tape\\广东电网-2";
        File file = new File(path);
        //2.指定切割秒数
        SplitWav.split(file,90);
    }
}
