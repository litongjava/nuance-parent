package com.hg.nve;

import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;

public class TTSWebSocketListener extends WebSocketAdapter {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    private WebSocket client = TTSClient.getClient();
    private SourceDataLine sdl = null;

    /*
     * 创建数据行
     */
    public SourceDataLine creteSourceDataLine(String rate) {
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
        // sampleRate - 每秒的样本数
        // sampleSizeInBits - 每个样本中的位数
        // channels - 声道数（单声道 1 个，立体声 2 个）
        // signed - 指示数据是有符号的，还是无符号的
        // bigEndian - 指示是否以 big-endian 字节顺序存储单个样本中的数据（false 意味着little-endian
        // pcm格式必须是false
        AudioFormat af = new AudioFormat(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
        SourceDataLine.Info info = new DataLine.Info(SourceDataLine.class, af);
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
        return sdl;
    }

    @Override
    public void onTextMessage(WebSocket websocket, String msg) throws Exception {
        log.info("从服务器接受到的消息是=" + msg);
        String text = TTSClient.getText();
        String msgSend = null;
        if (msg.startsWith("BEGIN_TTS_R")) {
            msgSend = "TTS_TEXT:" + text + "";
        } else if (msg.startsWith("TTS_TEXT_R")) {
            // audio/16L 22k 单声道
            msgSend = "STOP_TTS";
            String[] split = msg.split(":");
            // 提供给外部程序获取数据行
            TTSClient.setRate(split[split.length - 1]);
            // 构造播放所需的对象
            //creteSourceDataLine(TTSClient.rate);
        }

        if (msgSend != null) {
            client.sendText(msgSend);
            log.info("发送到服务的消息是=" + msgSend);
        }
    }

    @Override
    public void onBinaryMessage(WebSocket websocket, byte[] binary) throws Exception {
        OutputStream os = TTSClient.getOs();
        int len = binary.length;
        log.info("返回的语音流长度是= " + len);

        if (len > 0) {
            byte flag = binary[len - 1];
            os.write(binary, 0, len - 1);
            os.flush();

            //测试失败,边合成边播放,笔者测试失败
            // 播放流
            // if(!TTSClient.isStop()){
            // sdl.write(binary, 0, len - 1);
            // }
            // 播放流停止

            if (flag != 0) {
                log.info("流发送完毕");
                //sdl.close();
                CountDownLatch latch = TTSClient.getLatch();
                latch.countDown();
            }
        }
    }
}
