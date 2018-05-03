package com.hg.utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import org.junit.Test;

/**
 * @author litong
 * @date 2018年5月3日_上午11:35:12 
 * @version 1.0 
 */
public class RuntimeUtilTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	/**
	 * get os.name
	 * on windows 7 reuslt is Windows 7
	 * ont CentOS release 6.9 (Final) reuslt is  Linux
	 */
	@Test
	public void  getOsName() {
		String OSName= System.getProperty("os.name");
		System.out.println(OSName);
	}
	
	/**
	 * test exec method
	 * @throws IOException 
	 */
	@Test
	public void execTest() throws IOException {
		String exec = RuntimeUtil.exec("dir");
		System.out.println(exec);
		//默认情况下 windows控制填的编码是gbk,而eclipse控制台的编码是utf-8,会出现乱码
		String string = new String(exec.getBytes("GBK"),"UTF-8");
		//调整之后,问题没有解决
		System.out.println(string);
	}
	
	/**
	 * what is chartset.forname
	 */
	@Test
	public void testCharsetForname() {
		Charset forName = Charset.forName("GBK");
		System.out.println(forName);
	}
	
}
