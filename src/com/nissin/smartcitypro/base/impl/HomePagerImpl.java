package com.nissin.smartcitypro.base.impl;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nissin.smartcitypro.actvt.MainActivity;
import com.nissin.smartcitypro.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class HomePagerImpl extends BasePager {

	public HomePagerImpl(Activity mActivity) {
		super(mActivity);
	}

	@Override
	public View initView() {
		return super.initView();
	}

	@Override
	public void initData() {
		//toggle 是否有效
		ib_pager_toggle.setVisibility(View.INVISIBLE);
		setSlidingMenuEnable(false);
		
		tv_pager_title.setText("智慧北京");
		TextView tv = new TextView(mActivity);
		tv.setText("首页");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);
		fl_pager_body.addView(tv);
		super.initData();
	}
}
