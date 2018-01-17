package com.nte.wav.util;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

/**
 * Created by litong on 2017/12/14.
 */
public class GetWavNumChannels {
    //得到文件的声道数
    public static void main(String[] args) {
        File file = new File(args[0]);
        if(!file.exists()){
            System.out.println("文件不存在");
        }
        AudioInputStream ais =null;

        try {
            ais= AudioSystem.getAudioInputStream(file);
            AudioFormat af = ais.getFormat();
            System.out.println(af);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(ais!=null){
                try {
                    ais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
