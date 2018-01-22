package com.hg.nve;

import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.junit.Test;

import com.hg.utils.WAVUtil;

/**     
 * 创建人：litong  
 * 创建时间：2017年12月31日 下午9:14:25   
 * @version        
 */
public class TTSClientTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void test1() {
		String in = "c:/temp/out.pcm";
		String out = "c:/temp/out.wav";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String text = "我得名字是李通";
		String rate = TTSClient.playVoice(text, fos);
		try {
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(rate);
		WAVUtil.playPCM(in, rate);
	}
	
	@Test
	public void test2(){
		String in = "c:/temp/out.pcm";
		String out = "c:/temp/out.wav";
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String text = "中华人民共和国团结万岁";
		TTSClient.playVoice(text, fos);
	}
}