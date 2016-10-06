package com.nissin.smartcitypro.tools;

public class GlobelConnector {
	
	//http://127.0.0.2:8080/zhbj/categories.json     http://169.254.65.1:8080/zhbj/categories.json
	//public static String GLOBEL_SERVER = "http://10.0.2.2:8080/zhbj";
	public static String GLOBEL_SERVER = "http://169.254.65.1:8080/zhbj";
	//public static String GLOBEL_SERVER = "http://192.168.128.101:8080/zhbj";
	//public static String GLOBEL_SERVER = "http://172.20.10.3:8080/zhbj";
	//public static String GLOBEL_SERVER = "http://127.0.0.2:8080/zhbj";
	public static String GLOBEL_URL = GLOBEL_SERVER +"/categories.json";
	public static String PHOTOS_URL = GLOBEL_SERVER +"/photos/photos_1.json";
}
