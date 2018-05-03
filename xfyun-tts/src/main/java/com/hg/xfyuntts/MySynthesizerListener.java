package com.hg.xfyuntts;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SynthesizerListener;

/**
 * @author 李通 on 2018年2月12日 合成监听器
 * 
 */
public class MySynthesizerListener implements SynthesizerListener {
	
	private static final Logger log = LoggerFactory.getLogger(MySynthesizerListener.class);

	// 会话结束回调接口，没有错误时，error为null
	public void onCompleted(SpeechError error) {
	}

	// 缓冲进度回调
	// percent为缓冲进度0~100，beginPos为缓冲音频在文本中开始位置，endPos表示缓冲音频在文本中结束位置，info为附加信息。
	public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
	}

	// 开始播放
	public void onSpeakBegin() {
	}

	// 暂停播放
	public void onSpeakPaused() {
	}

	// 播放进度回调
	// percent为播放进度0~100,beginPos为播放音频在文本中开始位置，endPos表示播放音频在文本中结束位置.
	public void onSpeakProgress(int percent, int beginPos, int endPos) {
		log.info("percent is : " + percent);
	}

	// 恢复播放回调接口
	public void onSpeakResumed() {
	}

	@Override
	public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
	}

}
