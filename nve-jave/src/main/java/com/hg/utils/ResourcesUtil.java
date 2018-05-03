package com.hg.utils;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author litong
 * @date 2018年5月3日_上午10:27:19 
 * @version 1.0 
 */
public class ResourcesUtil {
	private static ResourceBundle bundle;
	static {
		bundle = ResourceBundle.getBundle("config",Locale.getDefault());
	}
	
	/**
	 * get value
	 */
	public static String getValue(String key) {
		return bundle.getString(key);
	}
	/**
	 * get bundle
	 */
	public static ResourceBundle getBundle() {
		return bundle;
	}
}
