package com.hg.nve.split.splitwav;



import java.io.File;

import javax.swing.filechooser.FileFilter;

public class XFileFilter extends FileFilter {

	@Override
	public boolean accept(File file) {
		// TODO Auto-generated method stub
		if(file.isDirectory()){
			return false;
		}else{
			if(file.getName().endsWith(".wav") || file.getName().endsWith(".WAV")){
				return true;
			}else{
				return false;
			}
		}
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".wav";
	}

}
