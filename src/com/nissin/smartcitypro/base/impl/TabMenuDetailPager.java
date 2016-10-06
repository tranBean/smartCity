package com.nissin.smartcitypro.base.impl;

import com.nissin.smartcitypro.base.BaseMenuDetailPager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class TabMenuDetailPager extends BaseMenuDetailPager {

	public String tabTitle;
	private TextView tv;
	public TabMenuDetailPager(Activity mActivity) {
		super(mActivity);
	}

	@Override
	public View initView() {
		tv = new TextView(mActivity);
		tv.setText("tab详情页面");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);
		return tv;
	}
	
	@Override
	public void initData() {
		tv.setText(tabTitle);
	}

}
