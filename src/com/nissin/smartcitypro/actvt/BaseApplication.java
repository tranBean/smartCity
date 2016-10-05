package com.nissin.smartcitypro.actvt;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
//注意必须清单中配置
public class BaseApplication extends Application {
	public static BaseApplication application;
	private static int mainTid;
	private static Handler handler;
	//必须在主线程运行
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		application = this;
		mainTid = android.os.Process.myTid();
		handler = new Handler();
	}
	
	public static Handler getHandler() {
		return handler;
	}

	public static Context getContextFromApplication()
	{
		return application;
	}
	
	public static int getMainTid()
	{
		return mainTid;
	}
}
