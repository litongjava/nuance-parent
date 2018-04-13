package com.hg.xfyuntts;

import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SynthesizeToUriListener;

public class MySynthesizeToUriListener implements SynthesizeToUriListener {
	/**
	 * progress,为合成进度0~100
	 */
	@Override
	public void onBufferProgress(int arg0) {
	}

	@Override
	public void onEvent(int arg0, int arg1, int arg2, int arg3, Object arg4, Object arg5) {
	}

	/**
	 * 会话合成完成回调接口 ,uri为合成保存地址，error为错误信息，为null时表示合成会话成功
	 */
	@Override
	public void onSynthesizeCompleted(String arg0, SpeechError arg1) {
	    System.out.println(arg0);
	    System.out.println(arg1);
	}

}
