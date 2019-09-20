package com.jnetdata.simple.constants;

/**
 * @author Administrator
 */
public class WebPathConstant {

    public static final String ROOT_HTML = "/simple";


    public static String getHtmlFilePath(String path) {
        return ROOT_HTML + path;
    }

}
