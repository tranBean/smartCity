package com.nissin.smartcitypro.base.leftimpl;

import com.nissin.smartcitypro.base.BaseMenuDetailPager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

public class PhotoMenuDetailPagerImpl extends BaseMenuDetailPager {

	public PhotoMenuDetailPagerImpl(Activity mActivity) {
		super(mActivity);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View initView() {
		TextView tv = new TextView(mActivity);
		tv.setText("详情页面-----组图");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);
		return tv;
	}

}
