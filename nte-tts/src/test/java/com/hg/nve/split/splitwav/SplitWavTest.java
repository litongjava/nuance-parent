package com.hg.nve.split.splitwav;

import static org.junit.Assert.fail;

import java.io.File;

import org.junit.Test;

public class SplitWavTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * 拆分语音
	 */
	@Test
	public void test1(){
		File file = new File("F://广东电网-3");
		SplitWav.split(file, 90);
	}

}
