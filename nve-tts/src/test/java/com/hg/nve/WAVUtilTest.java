package com.hg.nve;

import static org.junit.Assert.fail;

import java.io.ByteArrayOutputStream;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hg.utils.impl.WavUtilImplCommon;

/**     
 * 创建人：litong  
 * 创建时间：2017年12月31日 下午9:14:15   
 * @version        
 */
public class WAVUtilTest {
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void test1() {
		String in = "c:/temp/out.pcm";
		WavUtilImplCommon.playPCM(in, "22k");
	}

	@Test
	public void test2() {
		byte[] b = new byte[1024];
		int len = b.length;
		log.info("分配空间是的数据如下");
		for (int i = 0; i < len; i++) {
			System.out.print(b[i]);
			if (i % 20 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		for (int i = 0; i < len; i++) {
			b[i] = 1;
		}
		log.info("初始化后的数据如下");
		for (int i = 0; i < len; i++) {
			System.out.print(b[i]);
			if (i % 20==0) {
				System.out.println();
			}
		}
		System.out.println();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		baos.write(b, 0, b.length);

		log.info("写入后的数据如下");
		for (int i = 0; i < len; i++) {
			System.out.print(b[i]);
			if (i % 20 ==0) {
				System.out.println();
			}
		}
	}
}