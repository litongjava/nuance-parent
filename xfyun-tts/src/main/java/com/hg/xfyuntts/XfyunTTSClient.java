package com.hg.xfyuntts;

import com.iflytek.cloud.speech.Setting;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechSynthesizer;
import com.iflytek.cloud.speech.SpeechUtility;
import com.iflytek.cloud.speech.SynthesizeToUriListener;
import com.iflytek.cloud.speech.SynthesizerListener;

/**
 * @author 李通 on 2018年2月12日
 * 
 */
public class XfyunTTSClient {

	public static void main(String[] args) {
		// 显示日志
		Setting.setShowLog(true);
		String text = "尊敬的客户您好，汽车之家服务热线春节放假时间为2月15日至2月25日，值此新春之际祝您新年快乐！阖家幸福！";
		XfyunTTSClient.playText(text);
	}

	private static final String APPID = "5a274fb9";
	static {
		// 设置APPID,等号不可以省略
		SpeechUtility.createUtility(SpeechConstant.APPID + "=" + APPID);
	}

	/**
	 * 合成播放
	 * audio/L16;rate=8000;channel=1
	 */
	public static void playText(String text) {
		// 1.创建SpeechSynthesizer对象
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
		// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
		mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速
		mTts.setParameter(SpeechConstant.VOLUME, "80");// 设置音量，范围0~100
		mTts.setParameter(SpeechConstant.SAMPLE_RATE, "8000"); // 设置频率,默认 16000,设置为8000
		// 设置合成音频保存位置（可自定义保存位置），保存在“./tts_test.pcm”
		// 如果不需要保存合成音频，注释该行代码
		mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, "./tts_test.pcm");
		// 3.开始合成
		SynthesizerListener mSynListener = new MySynthesizerListener();
		mTts.startSpeaking(text, mSynListener);
	}

	/**
	 * 保存pcm到文件
	 * @param text 文字
	 */
	public static void toFile(String text, String targetFile,String sampleRate) {
		if (targetFile == null) {
			targetFile = "./tts_test.pcm";
		}
		// 1.创建SpeechSynthesizer对象
		SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer();
		// 2.合成参数设置，详见《MSC Reference Manual》SpeechSynthesizer 类
		mTts.setParameter(SpeechConstant.VOICE_NAME, "xiaoyan");// 设置发音人
		mTts.setParameter(SpeechConstant.SPEED, "50");// 设置语速，范围0~100
		mTts.setParameter(SpeechConstant.PITCH, "50");// 设置语调，范围0~100
		mTts.setParameter(SpeechConstant.VOLUME, "50");// 设置音量，范围0~100
		mTts.setParameter(SpeechConstant.SAMPLE_RATE, sampleRate);
		// 3.开始合成
		// 设置合成音频保存位置（可自定义保存位置），默认保存在“./tts_test.pcm”
		SynthesizeToUriListener synthesizeToUriListener = new MySynthesizeToUriListener();
		mTts.synthesizeToUri(text, targetFile, synthesizeToUriListener);
	}
}