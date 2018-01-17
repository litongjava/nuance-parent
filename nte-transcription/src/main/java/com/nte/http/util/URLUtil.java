package com.nte.http.util;

/**
 * Created by zhouyangying on 2017/9/19.
 */
public class URLUtil {
    /*
    验证url文件补头补尾
     */
    public static String valid(String url){
        if(url!=null){
            if(!url.startsWith("http://")){
                url="http://"+url;
            }

            if(!url.endsWith("/")){
                url+="/";
            }
        }
        return url;
    }
}
