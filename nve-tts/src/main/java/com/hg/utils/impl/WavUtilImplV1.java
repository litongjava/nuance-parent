//package com.hg.utils.impl;
//
//import java.io.OutputStream;
//
//import com.hg.utils.WavUtil;
//
///**
// * @author 李通 on 2018年2月12日
// */
//public class WavUtilImplV1 implements WavUtil {
//
//	@Override
//	public void pcmToWav(int NumChannels, int SampleRate, int BitsPerSample, String pcmFile, String wavFile) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void pcmToWav(int NumChannels, int SampleRate, int BitsPerSample, String pcmFile, OutputStream wavOS) {
//		// TODO Auto-generated method stub
//		
//	}
//	/**
//	 * {@inheritDoc}
//	 */
//	@Override
//	public void addWavHeader(int NumChannels, int SampleRate, int BitsPerSample, int Subchunk2Size,
//			OutputStream wavOS) {
//		byte[] header = new byte[44];
//		// Chunk ID
//		header[0] = 'R'; // RIFF/WAVE header
//		header[1] = 'I';
//		header[2] = 'F';
//		header[3] = 'F';
//		// Chunk Size
//		int chunkSize=Subchunk2Size+36;
//		header[4] = (byte) (chunkSize & 0xff);
//		header[5] = (byte) ((chunkSize >> 8) & 0xff);
//		header[6] = (byte) ((chunkSize >> 16) & 0xff);
//		header[7] = (byte) ((chunkSize >> 24) & 0xff);
//		// Fomat
//		header[8] = 'W';
//		header[9] = 'A';
//		header[10] = 'V';
//		header[11] = 'E';
//		// Subchunk 1 ID
//		header[12] = 'f'; // 'fmt ' chunk
//		header[13] = 'm';
//		header[14] = 't';
//		header[15] = ' ';
//		// SubChunk 1 Size
//		header[16] = 16; // 4 bytes: size of 'fmt ' chunk
//		header[17] = 0;
//		header[18] = 0;
//		header[19] = 0;
//		// Audio Format
//		header[20] = 1; // format = 1,1表示pcm
//		header[21] = 0;
//		// Num Channels
//		header[22] = (byte) channels;
//		header[23] = 0;
//		// Sample Rate
//		header[24] = (byte) (longSampleRate & 0xff);
//		header[25] = (byte) ((longSampleRate >> 8) & 0xff);
//		header[26] = (byte) ((longSampleRate >> 16) & 0xff);
//		header[27] = (byte) ((longSampleRate >> 24) & 0xff);
//		// Byte Rate
//		header[28] = (byte) (byteRate & 0xff);
//		header[29] = (byte) ((byteRate >> 8) & 0xff);
//		header[30] = (byte) ((byteRate >> 16) & 0xff);
//		header[31] = (byte) ((byteRate >> 24) & 0xff);
//		// block align
//		header[32] = (byte) (2 * 16 / 8);
//		header[33] = 0;
//		// bits per sample
//		header[34] = 16;
//		header[35] = 0;
//		// Sub chunk 2 ID
//		header[36] = 'd';
//		header[37] = 'a';
//		header[38] = 't';
//		header[39] = 'a';
//		// Sub chunk 2 size
//		header[40] = (byte) (totalAudioLen & 0xff);
//		header[41] = (byte) ((totalAudioLen >> 8) & 0xff);
//		header[42] = (byte) ((totalAudioLen >> 16) & 0xff);
//		header[43] = (byte) ((totalAudioLen >> 24) & 0xff);
//		out.write(header, 0, 44);
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void addWavHeader(int NumChannels, int SampleRate, int BitsPerSample, byte[] bytes, OutputStream wavOS) {
//		// TODO Auto-generated method stub
//		
//	}
//}