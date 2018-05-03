package com.hg.utils;

import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.Test;

import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.InputFormatException;

/**
 * @author litong
 * @date 2018年5月3日 下午3:09:15
 *
 */
public class NveJaveUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	//测试wav转mp3
	@Test
	public void test1() throws Exception{
		String source="D:\\音频\\out-13030030033-2092-20180119-171718-1516353438.84268.wav";
		String target="D:\\音频\\out-13030030033-2092-20180119-171718-1516353438.84268.mp3";
		JaveUtil.wavToMp3(source);
	}
	
	/**
	 * wav转mp3修改后测试
	 * @throws EncoderException 
	 * @throws InputFormatException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void test2() throws IllegalArgumentException, InputFormatException, EncoderException {
		String mediaFile="D:\\my_music\\爱的海洋.mp3";
		JaveUtil.mp3ToWav(mediaFile);
		//转换后的文件是 D:\my_music\爱的海洋.wav
	}
	/**
	 * 获取转换户的文件的信息
	 * @throws IOException 
	 */
	@Test
	public void test3() throws IOException {
		String mediaFile="D:\\my_music\\爱的海洋.wav";
		String mediaInfo = FfmpegUtil.getMediaInfo(mediaFile);
		// D:\my_music\爱的海洋.wav':  Duration: 00:03:46.6, bitrate: 128 kb/s    Stream #0.0: Audio: pcm_s16le, 8000 Hz
		System.out.println(mediaInfo);
	}
}
