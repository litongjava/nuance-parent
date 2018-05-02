package com.hg.utils;

import static org.junit.Assert.fail;

import org.junit.Test;

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
		NveJaveUtil.wavToMp3(source);
	}
}
