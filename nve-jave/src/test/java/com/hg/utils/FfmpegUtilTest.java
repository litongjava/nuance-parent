package com.hg.utils;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

/**
 * @author litong
 * @date 2018年5月3日_下午2:29:07 
 * @version 1.0 
 */
public class FfmpegUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	/**
	 * 获取声音信息的测试mp3文件
	 * @throws IOException
	 */
	@Test
	public void getMedisInfoTest() throws IOException {
		String mediaFile="D:\\my_music\\爱的海洋.mp3";
		String mediaInfo = FfmpegUtil.getMediaInfo(mediaFile);
		//爱的海洋.mp3':  Duration: 00:03:46.9, start: 0.000000, bitrate: 320 kb/s    Stream #0.0: Audio: mp3, 44100 Hz, stereo, 320 kb/sMust supply at least one output file
		System.out.println(mediaInfo);
	}
	/**
	 * 获取wav音频文件的格式
	 * @throws IOException 
	 */
	@Test
	public void getWavFormat() throws IOException {
		String mediaFile="D:\\dev_gitRepository\\nuance-parent\\nve-jave\\src\\main\\resources\\wav\\out-13796780807-2084-20180502-181801-1525256281.20465.wav";
		String mediaInfo = FfmpegUtil.getMediaInfo(mediaFile);
		//out-13796780807-2084-20180502-181801-1525256281.20465.wav':  Duration: 00:00:47.2, 
		//bitrate: 128 kb/s    Stream #0.0: Audio: pcm_s16le, 8000 Hz, mono, 128 kb/sMust supply at least one output file
		// why bitreate 128 kb/s
		// 8000*1*2*8= 128000 (b)=128kb 
		System.out.println(mediaInfo);
		
	}
}
