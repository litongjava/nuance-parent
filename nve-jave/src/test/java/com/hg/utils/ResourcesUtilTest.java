package com.hg.utils;

import static org.junit.Assert.*;

import java.util.Locale;

import org.junit.Test;

/**
 * @author litong
 * @date 2018年5月3日_上午10:27:36 
 * @version 1.0 
 */
public class ResourcesUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * get locale default code
	 * result zh_CN
	 */
	@Test
	public void test1() {
		Locale default1 = Locale.getDefault();
		System.out.println(default1);
	}
	
	/**
	 * get value
	 */
	@Test
	public void getKeyTest() {
		//String value = ResourcesUtil.getValue("ffmpeg_prog");
		String value = ResourcesUtil.getValue("welcome");
		System.out.println(value);
	}

}
