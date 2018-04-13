package com.hg.nve;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

/**
 * 创建人：litong 创建时间：2017年12月5日 下午9:27:56
 * 
 * @version 连接服务器,语音合成
 */
public class TTSClient {
	private static String text = null;
	private static final String serverUrl = "ws://192.168.28.185:8021";
	private static String voice = "Li-sa";
	private static String format = "PCM";// PCM / SPX
	private static OutputStream os=null;
	private static WebSocketFactory fac = new WebSocketFactory();
	private static WebSocket client = null;
	private static Logger log = LoggerFactory.getLogger(TTSClient.class);
	static String rate = null;
	private static final CountDownLatch latch = new CountDownLatch(1);
	//设定语音播放是否停止,true,停止
	private static boolean isStop=false;
	
	public static boolean isStop() {
		return isStop;
	}

	public static void setStop(boolean isStop) {
		TTSClient.isStop = isStop;
	}

	public static void setRate(String rate) {
		TTSClient.rate = rate;
	}

	// 设置语音包
	public static String getVoice() {
		return voice;
	}

	public static void setVoice(String voice) {
		TTSClient.voice = voice;
	}

	// 对外提供client
	public static WebSocket getClient() {
		return client;
	}

	public static CountDownLatch getLatch() {
		return latch;
	}
	//合成文本
	public static String getText() {
		return text;
	}
	
	//合成的语音流
	public static OutputStream getOs() {
		return os;
	}
	static {
		try {
			client = fac.createSocket(serverUrl);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 播放文本,返回流写入os
	 * @param oriText
	 * @param os
	 * @return
	 */
	public static String playVoice(String text, OutputStream os) {
		TTSClient.text = text;
		TTSClient.os=os;
		// final String text=replaceSpecStr(oriText);

		try {
			client.connect();
		} catch (WebSocketException e) {
			e.printStackTrace();
		}

		client.addListener(new TTSWebSocketListener());
		String message = "BEGIN_TTS:" + format + ":" + voice;
		client.sendText(message);
		log.info("发送到服务器的信息是=" + message);
		try {
			latch.await(60 * 5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
			client.disconnect();
		}
		return TTSClient.rate;
	}

	/**
	 * 去除所有特殊字符
	 */
	public static String replaceSpecStr(String orgStr) {
		if (null != orgStr && !"".equals(orgStr.trim())) {
			String regEx = "[\\s~·`!！@#￥$%^……&*（()）\\-——\\-_=+【\\[\\]】｛{}｝\\|、\\\\；;：:‘'“”\"，,《<。.》>、/？?]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(orgStr);
			return m.replaceAll("");
		}
		return null;
	}
}
