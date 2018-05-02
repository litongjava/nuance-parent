package com.hg.xfyuntts;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

import com.iflytek.cloud.speech.Setting;

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
		String pcmFile="./iamlitong.pcm";
		String sampleRete="8000";
	    XfyunTTSClient.toFile("我的名字是李通",pcmFile,sampleRete);
	    //WavUtilImplCommon.pcmToWav(pcmFile, "./test.wav", Integer.valueOf(sampleRete));
	}
	
	/**
	 * 合成文件播放
	 */
	@Test
	public void testPalyFile() {
		Setting.setShowLog(true);
	    XfyunTTSClient.playText("中华人民共和国");
	}

}
