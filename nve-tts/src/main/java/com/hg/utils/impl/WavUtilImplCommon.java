package com.hg.utils.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hg.utils.WavUtil;

/**
 * 创建人：litong 创建时间：2017年12月31日 下午9:14:10
 * 
 * @version
 */
public class WavUtilImplCommon{
	private static Logger log = LoggerFactory.getLogger(WavUtilImplCommon.class);

	public static void pcmToWav(String inFilename, String outFilename, String SampleRate) {
		int mSampleRate = 22000;
		if (SampleRate.equals("22k")) {
			mSampleRate = 22000;
		} else if (SampleRate.equals("16k")) {
			mSampleRate = 16000;
		} else if (SampleRate.equals("8k")) {
			mSampleRate = 8000;
		}
		int mBufferSize = 1024;

		FileInputStream in;
		FileOutputStream out;
		long totalAudioLen;
		long totalDataLen;
		long longSampleRate = mSampleRate;
		int channels = 1;
		long byteRate = 16 * mSampleRate * channels / 8;
		byte[] data = new byte[mBufferSize];
		try {
			in = new FileInputStream(inFilename);
			out = new FileOutputStream(outFilename);
			totalAudioLen = in.getChannel().size();
			totalDataLen = totalAudioLen + 36;

			addWaveHeader(out, totalAudioLen, totalDataLen, longSampleRate, channels, byteRate);
			while (in.read(data) != -1) {
				out.write(data);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将wav格式的头部信息写到信息写到输出流中
	 * 
	 * @param out 输出流
	 * @param totalAudioLen
	 * @param totalDataLen
	 * @param longSampleRate
	 * @param channels
	 * @param byteRate
	 * @throws IOException
	 */
	public static void addWaveHeader(OutputStream out, long totalAudioLen, long totalDataLen,
			long longSampleRate, int channels, long byteRate) throws IOException {
		byte[] header = new byte[44];
		// Chunk ID
		header[0] = 'R'; // RIFF/WAVE header
		header[1] = 'I';
		header[2] = 'F';
		header[3] = 'F';
		// Chunk Size
		header[4] = (byte) (totalDataLen & 0xff);
		header[5] = (byte) ((totalDataLen >> 8) & 0xff);
		header[6] = (byte) ((totalDataLen >> 16) & 0xff);
		header[7] = (byte) ((totalDataLen >> 24) & 0xff);
		// Fomat
		header[8] = 'W';
		header[9] = 'A';
		header[10] = 'V';
		header[11] = 'E';
		// Subchunk 1 ID
		header[12] = 'f'; // 'fmt ' chunk
		header[13] = 'm';
		header[14] = 't';
		header[15] = ' ';
		// SubChunk 1 Size
		header[16] = 16; // 4 bytes: size of 'fmt ' chunk
		header[17] = 0;
		header[18] = 0;
		header[19] = 0;
		// Audio Format
		header[20] = 1; // format = 1,1表示pcm
		header[21] = 0;
		// Num Channels
		header[22] = (byte) channels;
		header[23] = 0;
		// Sample Rate
		header[24] = (byte) (longSampleRate & 0xff);
		header[25] = (byte) ((longSampleRate >> 8) & 0xff);
		header[26] = (byte) ((longSampleRate >> 16) & 0xff);
		header[27] = (byte) ((longSampleRate >> 24) & 0xff);
		// Byte Rate
		header[28] = (byte) (byteRate & 0xff);
		header[29] = (byte) ((byteRate >> 8) & 0xff);
		header[30] = (byte) ((byteRate >> 16) & 0xff);
		header[31] = (byte) ((byteRate >> 24) & 0xff);
		// block align
		header[32] = (byte) (2 * 16 / 8);
		header[33] = 0;
		// bits per sample
		header[34] = 16;
		header[35] = 0;
		// Sub chunk 2 ID
		header[36] = 'd';
		header[37] = 'a';
		header[38] = 't';
		header[39] = 'a';
		// Sub chunk 2 size
		header[40] = (byte) (totalAudioLen & 0xff);
		header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
		header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
		header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
		out.write(header, 0, 44);
	}

	/**
	 * 只能播放wav文件
	 */
	public static void playWAV(String file) {
		File wavFile = new File(file);
		if (!wavFile.exists()) {
			log.info("指定的文件不存在");
			return;
		}
		AudioInputStream ais = null;
		try {
			ais = AudioSystem.getAudioInputStream(wavFile);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		AudioFormat af = ais.getFormat();
		log.info("每秒播放帧数：" + af.getSampleRate());
		log.info("总帧数：" + ais.getFrameLength());
		log.info("音频时长（秒）：" + ais.getFrameLength() / af.getSampleRate());

		Info info = new DataLine.Info(SourceDataLine.class, af);
		SourceDataLine sda = null;
		try {
			sda = (SourceDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}

		byte[] b = new byte[1024];
		int len = 0;

		try {
			sda.open(af, b.length);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		sda.start();

		try {
			while ((len = ais.read(b)) > 0) {
				// 开始播音,行将音频数据写入混频器
				sda.write(b, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ais.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sda.drain();
		sda.close();
	}

	/*
	 * 播放pcm文件
	 */
	public static void playPCM(String filename, String rate) {

		File file = new File(filename);
		if (!file.exists()) {
			log.info("文件不存在=" + file.getAbsolutePath());
			return;
		}

		int offset = 0;
		int bufferSize = Integer.valueOf(String.valueOf(file.length()));
		log.info("文件长度是:" + bufferSize);

		byte[] audioData = new byte[bufferSize];
		InputStream in = null;
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			in.read(audioData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		playPCMStream(audioData, rate);
	}

	/**
	 * 播放pcm字节流
	 */
	public static void playPCMStream(byte[] audioData, String rate) {
		float sampleRate = 22000;
		if (rate.equals("22k")) {
			sampleRate = 22000;
		} else if (rate.equals("16k")) {
			sampleRate = 16000;
		} else if (rate.equals("8k")) {
			sampleRate = 8000;
		}
		int sampleSizeInBits = 16;
		int channels = 1;
		boolean signed = true;
		boolean bigEndian = false;
		int bufferSize = audioData.length;
		// sampleRate - 每秒的样本数
		// sampleSizeInBits - 每个样本中的位数
		// channels - 声道数（单声道 1 个，立体声 2 个）
		// signed - 指示数据是有符号的，还是无符号的
		// bigEndian - 指示是否以 big-endian 字节顺序存储单个样本中的数据（false 意味着little-endian
		// pcm格式bigEndian值 必须是false
		AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
		SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, af, bufferSize);
		SourceDataLine sdl = null;
		log.info("每秒播放帧数：" + af.getSampleRate());
		try {
			sdl = (SourceDataLine) AudioSystem.getLine(info);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		}
		try {
			// 打开具有指定格式的行，这样可使行获得所有所需的系统资源并变得可操作。
			sdl.open(af);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		// 允许数据行执行数据 I/O
		sdl.start();

		int offset = 0;
		// 将流拆分进行播放

		while (offset < audioData.length) {
			offset += sdl.write(audioData, offset, audioData.length);
		}
	}
}
	
	