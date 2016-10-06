package com.nissin.smartcitypro.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager {

	public NoScrollViewPager(Context context) {
		super(context);
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		return false;//让该viewpager 禁止滑动
	}

	
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		return false;
	}

	/**
	 * 必须重写不然会报异常
	 * 10-05 16:27:01.262: E/AndroidRuntime(7876): java.lang.RuntimeException: Unable to start activity ComponentInfo{com.nissin.smartcitypro/com.nissin.smartcitypro.actvt.MainActivity}: android.view.InflateException: Binary XML file line #7: Error inflating class com.nissin.smartcitypro.view.NoScrollViewPager
	 * @param context
	 * @param attrs
	 */
	public NoScrollViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
}
