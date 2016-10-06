package com.nissin.smartcitypro.base.impl;

import com.nissin.smartcitypro.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SmartPagerImpl extends BasePager {

	public SmartPagerImpl(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		// TODO Auto-generated method stub
		return super.initView();
	}

	@Override
	public void initData() {
		setSlidingMenuEnable(true);
		tv_pager_title.setText("智慧服务");
		TextView tv = new TextView(mActivity);
		tv.setText("服务");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);
		fl_pager_body.addView(tv);
		super.initData();
	}

}
