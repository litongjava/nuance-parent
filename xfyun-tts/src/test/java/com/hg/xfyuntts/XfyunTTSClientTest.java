package com.hg.xfyuntts;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

public class XfyunTTSClientTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getPcmByteRate(){
		File file = new File("./tts_test.pcm");
		System.out.println(file.getTotalSpace());
	}
	/**
	 * 合成内容到文件
	 */
	@Test
	public void testToFile() {
	    XfyunTTSClient.toFile("我的名字是李通");
	}
	
	/**
	 * 合成文件播放
	 */
	@Test
	public void testPalyFile() {
	    XfyunTTSClient.playText("中华人民共和国");
	}

}
