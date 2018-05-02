package com.hg.nve.split.splitwav;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by litong on 2017/12/13.
 */
public class SplitWav {
	private static Logger log = LoggerFactory.getLogger(SplitWav.class);

	/**
	 * 拆分指定路径下的录音文件
	 */
	public static void split(File file, int splitSecond) {
		if (!file.exists()) {
			log.info("指定的文件夹不存在:" + file.getAbsolutePath());
		}
		recusiveFile(file, splitSecond);
	}

	public static void recusiveFile(File file, int splitSecond) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File file1 : files) {
				recusiveFile(file1, splitSecond);
			}
		} else {
			String name = file.getName();
			if (name.endsWith("wav") || name.endsWith("WAV")) {
				// 说明file1是个录音文件
				splitRealy(file, splitSecond);
			} else {
				log.info(file.getAbsolutePath() + ":不是wav文件");
			}
		}
	}

	/**
	 * 真正的开始拆分录音文件 假如录音名是a1.wav 拆分后的文件是a1_1.wav,a1_2.wav
	 */
	private static void splitRealy(File wav, int splitSecond) {
		log.info("文件字节数是:" + wav.length());
		AudioInputStream ais = null;
		float t1 = 0F;
		try {
			ais = AudioSystem.getAudioInputStream(wav);
			AudioFormat aFormat = ais.getFormat();

			log.info("每秒采样数是:" + aFormat.getSampleRate());
			log.info("总采样数:" + ais.getFrameLength());
			t1 = ais.getFrameLength() / aFormat.getSampleRate();
			log.info("录音时长:" + t1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ais != null) {
				try {
					ais.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}

		float v = (wav.length() - 44) / t1;
		int byteRate = (int) Math.ceil(v);

		log.info("每秒采样的字节数是:" + byteRate);

		// 得到int类型的时间长度

		int len = (int) Math.ceil(t1 / splitSecond);
		log.info("需要循环" + len + "次");
		if (len < 1) {
			log.info("文件长度不到指定的时间:" + wav.getAbsolutePath());
			return;
		}
		// 1.取出原文件的头部信息
		byte[] head = new byte[44];
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(wav);
			fis.read(head, 0, head.length);

			int start = 0;
			float end = 0F;
			for (int i = 0; i < len; i++) {
				log.info("第" + (i + 1) + "次截取");
				// 2.得到新的文件长度,替换到head中的头部信息
				start = i * splitSecond;
				end = (i + 1) * splitSecond;
				if (end > t1) {
					end = t1;
				}
				log.info("截取数据起始时间是:" + start);
				log.info("截取数据终止时间是:" + end);
				int startByte = start * byteRate;
				log.info("截取起始字节数是" + startByte);
				float endByte = end * byteRate;
				log.info("截取终止字节数是:" + endByte);
				int dataLength = (int) (endByte - startByte);
				log.info("截取数据长度是:" + dataLength);

				ByteBuffer buf1 = ByteBuffer.allocate(4);
				buf1.putInt(dataLength + 36);
				byte[] array1 = buf1.array();

				ByteBuffer buf2 = ByteBuffer.allocate(4);
				buf2.putInt(dataLength);
				byte[] array2 = buf2.array();
				// 一定要反序
				array1 = reverse(array1);
				array2 = reverse(array2);
				log.info("chunk size is:" + Arrays.toString(array1));
				log.info("sub chunk 2 size is:" + Arrays.toString(array2));

				for (int j = 0; j < 4; j++) {
					head[j + 4] = array1[j];
					head[j + 40] = array2[j];
				}
				// 拼接上头部信息,读入数据长度
				byte[] split = new byte[dataLength + 44];
				for (int j = 0, len2 = head.length; j < len2; j++) {
					split[j] = head[j];
				}
				fis.read(split, head.length, split.length - head.length);
				// 将声音文件写入硬盘
				String absName = wav.getAbsolutePath();
				String nameNoExt = absName.substring(0, absName.lastIndexOf('.'));
				File file = new File(nameNoExt);
				if (!file.exists()) {
					file.mkdirs();
				}
				String ext = absName.substring(absName.lastIndexOf("."), absName.length());
				String name = file.getName();
				String newAbsname = nameNoExt + "\\" + name + "_" + i + ext;
				FileOutputStream fos = null;
				try {
					fos = new FileOutputStream(newAbsname);
					fos.write(split);
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (fos != null) {
						fos.close();
					}
				}
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (ais != null) {
				try {
					ais.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 数组反转
	 *
	 * @param array
	 */
	public static byte[] reverse(byte[] array) {
		byte temp;
		int len = array.length;
		for (int i = 0; i < len / 2; i++) {
			temp = array[i];
			array[i] = array[len - 1 - i];
			array[len - 1 - i] = temp;
		}
		return array;
	}
}
