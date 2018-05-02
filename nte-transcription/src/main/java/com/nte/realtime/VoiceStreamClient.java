package com.nte.realtime;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;

import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;

import com.alibaba.fastjson.JSON;
import com.nte.realtime.StreamRequest.Channel;
import com.nte.realtime.StreamResponse.Lattice;
import com.nte.realtime.StreamResponse.Link;

public class VoiceStreamClient {

	private String serverUrl = "";

	public VoiceStreamClient(String serverUrl) {
		super();
		this.serverUrl = serverUrl;
	}

	private String reference;

	public void start() {
		/**
		 * 实例化请求对象
		 */
		StreamRequest request = new StreamRequest();
		request.addChannel(new Channel("test-001", "audio/L16"));
		// request.addChannel(new Channel("test-001", "audio/wave"));
		StringEntity entity;
		try {
			entity = new StringEntity(JSON.toJSONString(request));
			entity.setContentType("application/json");
			reference = request.getReference();
			String body = HTTPClient.post(this.serverUrl + "incremental", entity);
			System.out.println(body);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getStatus() {
		try {
			String body = HTTPClient.get(this.serverUrl + "incremental/status/" + this.reference);
			StreamResponse response = JSON.parseObject(body, StreamResponse.class);
			com.nte.realtime.StreamResponse.Channel channel = response.getChannels().get("test-001");
			if (channel != null) {
				Lattice lattice = channel.getPartialLattice();
				if (lattice != null) {
					for (Link link : lattice.links()) {
						System.out.print(link.getWord());
					}
					System.out.println();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void send(String channelName, byte[] voiceData, boolean eos) {
		try {
			ByteArrayEntity entity = new ByteArrayEntity(voiceData, ContentType.create("audio/L16"));
			// ByteArrayEntity entity = new ByteArrayEntity(voiceData,
			// ContentType.create("audio/wave"));
			Map<String, String> headers = new HashMap<>();
			headers.put("Content-Disposition", "attachment; name=\"" + channelName + "\"");
			System.out.println(HTTPClient.put(this.serverUrl + "incremental/" + (eos ? "eos/" : "") + this.reference,
					headers, entity));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String server_url = "http://192.168.28.182:8100/";

	public static void main(String[] args) throws InterruptedException, LineUnavailableException, IOException {
		final VoiceStreamClient client = new VoiceStreamClient(server_url);
		client.start();
		/*
		 * audioFormat：音频文件个格式 设置音频格式 音频文件的编码方式 采样率，每秒采集的样本数 每个样本的位数
		 */
		AudioFormat audioFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, 8000f, 16, 1, 2, 8000f, false);
		System.out.println(audioFormat);
		/*
		 * 数据行信息
		 */
		DataLine.Info info = new DataLine.Info(TargetDataLine.class, audioFormat);

		try {
			/*
			 * 通过数据行信息获取目标数据行
			 */
			final TargetDataLine td = (TargetDataLine) (AudioSystem.getLine(info));
			/*
			 * 根据硬音频格式打开数据行
			 */
			td.open(audioFormat);
			/*
			 * 打开IO
			 */
			td.start();
			new Thread(new Runnable() {
				public void run() {
					int len = -1;
					byte[] buffer = new byte[1024];
					int count = 0;
					while ((len = td.read(buffer, 0, 1024)) != -1) {
						byte[] voiceData = new byte[len];
						System.arraycopy(buffer, 0, voiceData, 0, len);
						count++;
						client.send("test-001", voiceData, count > 800);
						client.getStatus();
						if (count > 800) {
							break;
						}
					}
				}
			}).start();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		/*
		 * InputStream is = VoiceStreamClient.class.getResourceAsStream("test.wav"); int
		 * len = -1; byte[] buffer = new byte[1024]; while((len = is.read(buffer, 0,
		 * 1024))!=-1){ byte[] voiceData = new byte[len]; System.arraycopy(buffer, 0,
		 * voiceData, 0, len); client.send("test-001", voiceData,false);
		 * client.getStatus(); } client.send("test-001", new byte[0],true);
		 * client.getStatus(); client.getStatus(); client.getStatus();
		 */
		Thread.sleep(1000000000L);

	}

}
