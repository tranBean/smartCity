package com.nissin.smartcitypro.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nissin.smartcitypro.actvt.MainActivity;
import com.nissin.smartcitypro.base.BaseMenuDetailPager;
import com.nissin.smartcitypro.base.BasePager;
import com.nissin.smartcitypro.base.leftimpl.InteractMenuDetailPagerImpl;
import com.nissin.smartcitypro.base.leftimpl.NewsMenuDetailPagerImpl;
import com.nissin.smartcitypro.base.leftimpl.PhotoMenuDetailPagerImpl;
import com.nissin.smartcitypro.base.leftimpl.TopicMenuDetailPagerImpl;
import com.nissin.smartcitypro.bean.NewsInfos;
import com.nissin.smartcitypro.bean.NewsInfos.NewsMenuInfo;
import com.nissin.smartcitypro.bean.NewsInfos.NewsTabInfo;
import com.nissin.smartcitypro.fragmt.LeftMenuFragmt;
import com.nissin.smartcitypro.tools.GlobelConnector;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class NewsPagerImpl extends BasePager {

	private NewsInfos newsInfos;
	public List<BaseMenuDetailPager> detailPagers;

	public NewsPagerImpl(Activity mActivity) {
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
		tv_pager_title.setText("新闻中心");
		getDataFromService();
	}
	//从服务器请求数据
	private void getDataFromService() {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, GlobelConnector.GLOBEL_URL, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo responseInfo) {
				String rusult = (String) responseInfo.result;
				parseData(rusult);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, 0).show();
				error.printStackTrace();
			}
		});
	}
	/**
	 * 解析网络数据
	 * @param rusult
	 */
	protected void parseData(String rusult) {
		Gson gson = new Gson();
		newsInfos = gson.fromJson(rusult, NewsInfos.class);
		
		//向左侧边栏提供服务器数据
		MainActivity mainActivity = (MainActivity) mActivity;
		LeftMenuFragmt leftMenufragmt = mainActivity.getLeftMenuFragtObj();
		leftMenufragmt.setDataFromService(newsInfos);
		
		//管理四个菜单详情页
		NewsMenuInfo newsMenuInfo = newsInfos.data.get(0);
		ArrayList<NewsTabInfo> children = newsMenuInfo.children;
		
		detailPagers = new ArrayList<BaseMenuDetailPager>();
		detailPagers.add(new NewsMenuDetailPagerImpl(mActivity,children));
		detailPagers.add(new TopicMenuDetailPagerImpl(mActivity));
		detailPagers.add(new PhotoMenuDetailPagerImpl(mActivity));
		detailPagers.add(new InteractMenuDetailPagerImpl(mActivity));
		
		setCurMenuDetailPagerImpl(0);//手动默认第一次进入显示菜单新闻详情页的
		/*leftMenufragmt.mPosition = 0;
		leftMenufragmt.leftMenuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mPosition = position;
				mListViewAdapter.notifyDataSetChanged();
				//在FrameLayout展示详情页界面
				setCurMenuDetailPagerImpl(position);
				slidingToggle();
			}
		
		});*/
	}
	
	//在FrameLayout展示详情页界面
	public void setCurMenuDetailPagerImpl(int position)
	{
		fl_pager_body.removeAllViews();//刷新上次显示的菜单标签界面
		fl_pager_body.addView(detailPagers.get(position).mRootView);
		tv_pager_title.setText(newsInfos.data.get(position).title);
		
		
		//初始化当前页面的数据！！！！！！注意！！
		detailPagers.get(position).initData();
	}
}
