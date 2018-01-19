package com.hg.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {

	private Logger log = LoggerFactory.getLogger(this.getClass());
	public String getString(String filename){
		File file = new File(filename);
		if(!file.exists()){
			log.info("文件不存在:"+file.getAbsolutePath());
		}
		FileReader reader=null;
		try {
			 reader= new FileReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		BufferedReader bufRed = new BufferedReader(reader);
		StringBuilder sb = new StringBuilder();
		try {
			while((bufRed.read())!=-1){
				sb.append(bufRed.readLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(bufRed!=null){
				try {
					bufRed.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(reader!=null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return sb.toString();
	}
}