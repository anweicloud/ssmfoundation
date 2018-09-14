package com.anwei.common.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * url转码、解码
 * @author Anwei
 * @date 2018年1月12日
 */
public class URLUtil {
    private final static String ENCODE = "UTF-8"; 
    
    public static String decoder(String str) {
    	return decoder(str, ENCODE);
    }
    
    public static String encoder(String str) {
    	return encoder(str, ENCODE);
    }
    
    /**
     * URL 解码
     * @param s
     * @param enc
     * @return
     */
    public static String decoder(String s, String enc) {
        String result = "";
        if (null == s) {
            return "";
        }
        try {
            result = URLDecoder.decode(s, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * URL 转码
     * @param s
     * @param enc
     * @return
     */
    public static String encoder(String s, String enc) {
        String result = "";
        if (null == s) {
            return "";
        }
        try {
            result = URLEncoder.encode(s, enc);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "测试1";
        System.out.println(encoder(str));
        System.out.println(decoder(str));
        
    }

}