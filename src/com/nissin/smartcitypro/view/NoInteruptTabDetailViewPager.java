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

	private int rawX;
	private int rawY;
	private int endX;
	private int endY;


	public NoInteruptTabDetailViewPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public NoInteruptTabDetailViewPager(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 1.右滑且是第一个页面，需要父，祖宗控件拦截
	 * 2.左滑且是最后一个页面，需要父，祖宗控件拦截
	 * 3.上下滑，需要父，祖宗控件拦截
	 */

	// 让父控件不要拦截
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		// 如果是第一个父控件，请求父控件拦截
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN://不拦截，保证ACTION_MOVE可以实现
			getParent().requestDisallowInterceptTouchEvent(true);//不拦截
			rawX = (int) ev.getRawX();
			rawY = (int) ev.getRawY();
			
			break;
		case MotionEvent.ACTION_MOVE:
			endX = (int) ev.getRawX();
			endY = (int) ev.getRawY();
			
			if(Math.abs(endX - rawX) > Math.abs(endY - rawY))
			{//左右滑
				if(endX - rawX > 0)//右滑
				{
					if(getCurrentItem() == 0)
					{
						System.out.println("我调用了！");
						getParent().requestDisallowInterceptTouchEvent(false);//要拦截
					}/*else
					{
						getParent().requestDisallowInterceptTouchEvent(true);//不要拦截
					}*/
				}else//左滑
				{
					if(getCurrentItem() == getAdapter().getCount() - 1)//左滑最后一个
					{
						getParent().requestDisallowInterceptTouchEvent(false);//要拦截
					}
				}
			}else
			{//上下滑
				getParent().requestDisallowInterceptTouchEvent(false);//要拦截
				
			}
			break;

		default:
			break;
		}

		return super.dispatchTouchEvent(ev);
	}

}
