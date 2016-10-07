package com.nissin.smartcitypro.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 请求父控件 不要拦截子控件的touch事件
 * 
 * @author Administrator
 *
 */
public class NoInteruptTabDetailViewPager extends ViewPager {

	public NoInteruptTabDetailViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoInteruptTabDetailViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	// 让父控件不要拦截
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// 如果是第一个父控件，请求父控件拦截

		getParent().requestDisallowInterceptTouchEvent(true);

		return super.dispatchTouchEvent(ev);
	}

}
