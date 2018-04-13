package com.hg.test;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WavInfo {
	
	public static void main(String[] args) {
		Logger log = LoggerFactory.getLogger(WavInfo.class);
		String filename="C:\\Users\\zhouyangying\\Desktop\\录音\\123.wav";
		File file = new File(filename);
		if(!file.exists()){
			log.info("文件不存在:"+file.getAbsolutePath());
			return ;
		}
		AudioInputStream ais=null;
		try {
			ais = AudioSystem.getAudioInputStream(file);
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		AudioFormat af = ais.getFormat();
		log.info("文件总字节数:"+file.length());
		float sampleRate = af.getSampleRate();
		log.info("文件每秒采样数:"+sampleRate);
		//总采样数
		long frameLength = ais.getFrameLength();
		log.info(""+frameLength);
		//每个样本的大小
		int frameSize = af.getFrameSize();
		float t=frameLength/sampleRate;
		log.info(""+t);
		float frameRate = af.getFrameRate();
		log.info(""+frameRate);
	}
	@Test
	public void test1(){
		
	}

	
}
