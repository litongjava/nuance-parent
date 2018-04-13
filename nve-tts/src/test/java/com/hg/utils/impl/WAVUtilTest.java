package com.hg.utils.impl;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

public class WAVUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void longToInt() {
		long l = 0xff00L;
		long result = l & 0xffff;
		System.out.println(result);
	}

	// pcm <==> wav
	@Test
	public void getFile() {
		String path = "C:\\Users\\Administrator\\wav\\上班时间.wav";
		File file = new File(path);
		System.out.println(file.getTotalSpace() / 8);
		// 文件大小是 1477 4945 280b这 chunkSize=1477 4945 272
		// String binaryString = Integer.toBinaryString(14774945272);
		// 上班时间.wav[3-7]=86820100
		int i=0x86820100;
		String string = Integer.toString(i, 16);
		
		System.out.println(string);
		System.out.println(i);
		
	}
}
