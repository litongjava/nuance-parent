package com.hg.utils;

import java.io.IOException;

/**
 * @author litong
 * @date 2018年5月3日_上午10:42:24 
 * @version 1.0
 * ffmpeg命令的工具类,运行在windows系统上 
 */
public class FfmpegUtil {
	private static String ffmpegProg=null;
	static {
		ffmpegProg=ConfigUtil.getProperty("ffmpeg_prog");
	}

	/**
	 * 使用命令
	 * ffmpeg -i <mediaFile> 
	 * 返回文件中的信息
	 * @param mediaFile
	 * @return
	 * @throws IOException 
	 */
	public static String getMediaInfo(String mediaFile) throws IOException {
		String retval=null;
		String cmd=ffmpegProg+" -i "+mediaFile;
		retval= RuntimeUtil.exec(cmd);
		return retval;
	}
}
