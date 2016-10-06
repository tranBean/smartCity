package com.nissin.smartcitypro.base;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.actvt.MainActivity;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * 主页面5大实现的基类
 * 
 * @author Administrator
 *
 */
public class BasePager {
	
	public Activity mActivity;
	public TextView tv_pager_title;
	public FrameLayout fl_pager_body;
	public View mBaseView;
	public ImageButton ib_pager_toggle;
	
	public BasePager(Activity mActivity) {
		this.mActivity = mActivity;
		initView();
	}
	//初始化页面
	public View initView() {
		mBaseView = View.inflate(mActivity, R.layout.base_pager, null);
		tv_pager_title = (TextView) mBaseView.findViewById(R.id.tv_pager_title);
		fl_pager_body = (FrameLayout) mBaseView.findViewById(R.id.fl_pager_body);
		ib_pager_toggle = (ImageButton) mBaseView.findViewById(R.id.ib_pager_toggle);
		ib_pager_toggle.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingToggle();
			}
		});
		return mBaseView;
	}
	//初始数据
	public void initData() {

	}
	//设置侧边栏是否可以拉出
	public void setSlidingMenuEnable(boolean enable)
	{
		MainActivity mainActivity = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		if(enable)
		{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else
		{
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}
	
	//侧边栏toggle
		protected void slidingToggle() {
			MainActivity mainActivity = (MainActivity) mActivity;
			SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
			slidingMenu.toggle();
		}
}
