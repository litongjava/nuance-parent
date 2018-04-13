package com.hg.utils;

import java.io.OutputStream;

/**
 * @author 李通 on 2018年2月12日
 * 
 *         wav文件格式工具类
 */
public interface WavUtil {
	/**
	 * 将pcm文件转换为wav文件
	 * 
	 * @param pcmFile
	 *            pcm文件路径
	 * @param NumChannels
	 *            pcm文件通道数,eg:1
	 * @param SampleRate
	 *            pcm文件一秒钟采样的格式,采样频率,eg:8000
	 * @param BitsPerSample
	 *            pcm文件每个样本的大小,采样位数 eg:16
	 * @param wavFile
	 *            转换后的wav文件路径
	 */
	public void pcmToWav(int NumChannels, int SampleRate, int BitsPerSample, String pcmFile, String wavFile);

	/**
	 * @param wavOS
	 *            转换后wav文件的输出流
	 */
	public void pcmToWav(int NumChannels, int SampleRate, int BitsPerSample, String pcmFile, OutputStream wavOS);

	/**
	 * 根据指定的信息生成wav文件的头部,保存在wos中
	 * 
	 * @param Subchunk2Size
	 *            pcm文件的大小,unit bit,eg 100000;
	 */
	public void addWavHeader(int NumChannels, int SampleRate, int BitsPerSample, int Subchunk2Size, OutputStream wavOS);

	/**
	 * 根据指定的信息生成wav文件的流,保存在wos中
	 * 
	 * @param bytes
	 *            pcm文件的流
	 */
	public void addWavHeader(int NumChannels, int SampleRate, int BitsPerSample, byte[] bytes, OutputStream wavOS);
}