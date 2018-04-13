package com.hg.utils.impl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class WavUtilImplCommonTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * 测试播报转写
	 */
	@Test
	public void test2(){
		/**
		 * 科大讯飞的合成,
		 * 8k:语速慢
		 * 16K:正常余语速,原声音
		 * 22k:语速快,是精灵音
		 */
		WavUtilImplCommon.playPCM("D:\\dev_gitRepository\\nuance-parent\\xfyun-tts\\tts_test.pcm", "22k");
	}
	
	@Test
	public void test3(){
		WavUtilImplCommon.pcmToWav("D:\\dev_gitRepository\\nuance-parent\\xfyun-tts\\tts_test.pcm", "./tts_test.wav", "8k");
	}
	
	/**
	 * 得出chunk size 的代码
	 */
	@Test
	public void test4(){
		File file = new File("C:\\Users\\Administrator\\wav\\上班时间.wav");
		long totalSpace = file.getTotalSpace();
		System.out.println("文件的bit数是"+totalSpace);
		System.out.println("文件的byte数是"+file.getTotalSpace()/8);
	}

}
