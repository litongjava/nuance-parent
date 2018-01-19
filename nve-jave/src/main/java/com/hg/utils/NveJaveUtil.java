package com.hg.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;


import it.sauronsoftware.jave.AudioAttributes;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.EncodingAttributes;
import it.sauronsoftware.jave.InputFormatException;

public class NveJaveUtil {
    private static Logger log = Logger.getLogger(NveJaveUtil.class);

    /**
     * 格式话音频文件为wav格式
     *
     * @throws IllegalArgumentException
     * @throws InputFormatException
     * @throws EncoderException
     */
    public static String formatMp3ToWav(String filename) throws IllegalArgumentException, InputFormatException, EncoderException {
        File file = new File(filename);

        String pathFile=filename;
        String path = pathFile.substring(0, pathFile.lastIndexOf("\\") + 1);
        String fileName = pathFile.substring(pathFile.lastIndexOf("\\") + 1, pathFile.lastIndexOf("."));

        String s = path + fileName + ".wav";


        File target = new File(path + fileName + ".wav");


        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("pcm_s16le");
        audio.setBitRate(new Integer(256));
        audio.setChannels(new Integer(1));
        audio.setSamplingRate(new Integer(16000));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setFormat("wav");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        try {
            encoder.encode(file, target, attrs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("转换完成:"+target.getAbsolutePath());
        return target.getAbsolutePath();
    }

    public static Map<String,String>  formatMp3ToWav(List<String> wavs) throws EncoderException {
        Map<String,String> result=new HashMap<>();
        if(wavs==null || wavs.size()<0){
            log.info("传入的list为空");
            return null;
        }
        for (String wav : wavs) {
            String target = formatMp3ToWav(wav);
            result.put(wav,target);
        }
        return result;
    }

//    public static void main(String[] args) {
//        try {
//            formatToWav("D:\\Program Files\\apache-tomcat-7-8000\\webapps\\tape\\t92022000_20180110_085357.mp3");
//
//        } catch (IllegalArgumentException e) {
//            log.info("参数不合法");
//            e.printStackTrace();
//        } catch (InputFormatException e) {
//            log.info("输入文件格式异常");
//            e.printStackTrace();
//        } catch (EncoderException e) {
//            log.info("编码异常");
//            e.printStackTrace();
//        }
//    }
}
