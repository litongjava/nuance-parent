package com.hg.nve.split.splitwav;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class FileActionListener implements ActionListener {
	private String path = "";
	
	private JButton jb;
	private JButton jb1;
	private JButton jb2;
	
	private JLabel jl;
	private JLabel jl1;
	
	private JPanel jp;
	
	public FileActionListener(JButton jb,JButton jb1,JButton jb2,JLabel jl,JLabel jl1,JPanel jp){
		this.jb = jb;
		this.jl = jl;
		this.jp = jp;
		this.jb1 = jb1;
		this.jb2 = jb2;
		this.jl1 = jl1;
	} 

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JFileChooser chooser = new JFileChooser();             //设置选择器  
	   	 chooser.setMultiSelectionEnabled(false);             //设为多选  
	   	 chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
	   	 /*chooser.setAcceptAllFileFilterUsed(false);
	   	 chooser.setFileFilter(new XFileFilter());*/
	   	int returnVal = chooser.showOpenDialog(jb);        //是否打开文件选择框  
	   	System.out.println("returnVal="+returnVal);    
	   	if (returnVal == JFileChooser.APPROVE_OPTION) {          //如果符合文件类型  
	   		jp.remove(jb1);
	   		jp.remove(jl1);
	   		jp.remove(jb2);
	       	path = chooser.getSelectedFile().getAbsolutePath();      //获取绝对路径  
	       	if(chooser.getSelectedFile().isDirectory()){
	       		jl.setText("文件夹：" + chooser.getSelectedFile().getName());	       		
	       	}else{
	       		if(chooser.getSelectedFile().getName().endsWith(".wav") || chooser.getSelectedFile().getName().endsWith(".WAV")){
	       			jl.setText("文件名：" + chooser.getSelectedFile().getName());	       			       			       			
	       		}else{
	       			jl.setText("请选择wav文件");
	       			return;
	       		}
	       	}
	       	jp.add(jb1);
	       	jl1.setText("");
	   		jp.add(jl1);
	       	jb1.addMouseListener(new MouseAdapter() {
	       		@Override
	       		public void mouseClicked(MouseEvent e) {
	       			jl1.setText("正在切割...");
	       			final File file = new File(path);
	       	        SplitWav.split(file,90);
	       	        jl1.setText("切割完成");
	       	        jp.add(jb2);
	       	        jb2.addMouseListener(new MouseAdapter() {
	       	        	@Override
	       	        	public void mouseClicked(MouseEvent e) {
	       	        		if(file.isDirectory()){
	       	        			try {
									java.awt.Desktop.getDesktop().open(file);
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}	       	        			
	       	        		}else{
	       	        			try {
									java.awt.Desktop.getDesktop().open(file.getParentFile());
								} catch (IOException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
	       	        		}
	       	        	}
					});
	       		}
			});
	   	}  
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
