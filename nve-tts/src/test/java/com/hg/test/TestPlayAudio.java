package com.hg.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.Test;

import sun.audio.AudioPlayer;

/**
 * 创建人：litong 创建时间：2017年12月7日 下午2:13:05
 * 
 * @version 测试最适合播放声音的方法
 */
public class TestPlayAudio {

	@SuppressWarnings("restriction")
	@Test
	public void test1() {
		String file = "c:/temp/out.wav";
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(fis);
	}
}
