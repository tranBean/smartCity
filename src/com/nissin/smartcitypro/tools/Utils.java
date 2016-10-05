package com.nissin.smartcitypro.tools;

import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.actvt.BaseActivity;
import com.nissin.smartcitypro.actvt.BaseApplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class Utils {

	public static String[] getResourcesStringArray(int tabNames)
	{
		return getResource().getStringArray(tabNames);
	}
	
	//context.getResource();
	public static Context getContext()
	{
		return BaseApplication.getContextFromApplication();
	}
	
	public static Resources getResource() {
		return BaseApplication.getContextFromApplication().getResources();
	}
	
	/** dip×ª»»px */
	public static int dip2px(int dip) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (dip * scale + 0.5f);
	}

	/** pxz×ª»»dip */

	public static int px2dip(int px) {
		final float scale = getResource().getDisplayMetrics().density;
		return (int) (px / scale + 0.5f);
	}

	public static void runOnUiThread(Runnable runnable) {
		//如果当前线程在主线程运行
		if(android.os.Process.myTid() == BaseApplication.getMainTid())
		{
			runnable.run();
		}else
		{//子线程的话，拿到handler
			BaseApplication.getHandler().post(runnable);
		}
	}

	/*public static Drawable getDrawable(int nothing) {
		return getResource().getDrawable(R.drawable.nothing);
	}*/

	public static int getDimens(int homePictureHeight) {
		
		return (int) getResource().getDimension(homePictureHeight);
	}

	public static void postDelay(Runnable autoTask, int time) {
		BaseApplication.getHandler().postDelayed(autoTask, time);
	}

	public static void cancelRunnable(Runnable runnable)
	{
		BaseApplication.getHandler().removeCallbacks(runnable);
	}

	public static void startActivity(Intent intent) {
		if(BaseActivity.baseActivity == null)
		{
			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			getContext().startActivity(intent);
		}else
		{
			BaseActivity.baseActivity.startActivity(intent);
		}
		
	}
}
