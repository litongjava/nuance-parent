package com.hg.test;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyRecord extends JFrame implements ActionListener {

	//定义录音格式
	AudioFormat af=null;
	//定义目标数据行,从中取音频数据,
	TargetDataLine td=null;
	//定了源数据行,写入数据到源数据行,源数据行传递到混音器
	SourceDataLine sd=null;
	
	//定义字节数组输入输出流,
	ByteArrayInputStream baos=null;
	ByteArrayOutputStream bain=null;
	
	//定义音频流
	AudioInputStream ais=null;
	
	//定义停止录音的标志
	boolean stopFlag=false;
	
	
	//定义所需要的组件
	JPanel jp1,jp2,jp3;
	JLabel jl1=null;
	JButton captureBtn,stopBtn,playBtn,saveBtn;
	public static void main(String[] srgs){
		MyRecord myRecord = new MyRecord();

	}
	
	//伪构造函数
	public MyRecord() {
		//初始化组件
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		//定义字体
		Font myFont = new Font("华文新魏",Font.BOLD,30);
		jl1.setFont(myFont);
		jp1.add(jl1);
		
		captureBtn=new JButton("开始录音");
		//对开始按钮进行注册监听
		captureBtn.addActionListener(this);
		captureBtn.setActionCommand("captureBtn");
		//对停止录音进行注册
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
