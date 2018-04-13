package com.hg.nve.split.splitwav;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Created by litong on 2017/12/13.
 */
public class Demo {
    public static void main(String[] args) {
        //1.指定文件夹
        String path="";
        JFrame jf = new JFrame("录音文件切割");
        JPanel jp = new JPanel();
        jf.add(jp);
        JButton jb = new JButton("选择文件");
        jp.add(jb);
        JLabel jl = new JLabel("请选择文件");
        jp.add(jl);
        JButton jb1 = new JButton("分割文件");
       	JLabel jl1 = new JLabel("");
       	JButton jb2 = new JButton("打开文件夹");
	
	    jb2.setOpaque(false);
        jb.addActionListener(new FileActionListener(jb,jb1,jb2,jl,jl1,jp));
        jf.setBounds(400, 200, 500, 500);  
        jf.setVisible(true);  
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
       /* */
    }
}
