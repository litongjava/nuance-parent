package com.hg.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by litong on 2018/4/11 0011.
 * 读取配置文件
 */
public class ConfigUtil {
    private static final String CONFIG_PATH="config.properties";
    private static Properties prop = new Properties();
    static{
        InputStream ins = ConfigUtil.class.getClassLoader().getResourceAsStream(CONFIG_PATH);
        try {
            prop.load(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回属性
     * @param key
     * @return
     */
    public static String getProperty(String key){
        return prop.getProperty(key);
    }

    public static Properties getProperties(){
        return prop;
    }
}
