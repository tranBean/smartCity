package com.nissin.smartcitypro.base.impl;

import com.nissin.smartcitypro.base.BasePager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class SettingPagerImpl extends BasePager {

	public SettingPagerImpl(Activity mActivity) {
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
		// toggle 是否有效
		ib_pager_toggle.setVisibility(View.INVISIBLE);
		setSlidingMenuEnable(false);

		tv_pager_title.setText("设置中心");
		TextView tv = new TextView(mActivity);
		tv.setText("设置");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);
		fl_pager_body.addView(tv);
		super.initData();
	}

}
