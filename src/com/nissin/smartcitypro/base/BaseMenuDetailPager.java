package com.nissin.smartcitypro.base;

import android.app.Activity;
import android.view.View;

public abstract class BaseMenuDetailPager {
	public Activity mActivity;
	public View mRootView;
	
	public BaseMenuDetailPager(Activity mActivity) {
		this.mActivity = mActivity;
		this.mRootView = initView();
	}
	//初始化界面
	public abstract View initView();
	//初始化数据
	public void initData()
	{
		
	}
}
