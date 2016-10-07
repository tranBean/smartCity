package com.nissin.smartcitypro.base.impl;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.base.BaseMenuDetailPager;
import com.nissin.smartcitypro.bean.TabData;
import com.nissin.smartcitypro.bean.TabData.TopNewsData;
import com.nissin.smartcitypro.tools.GlobelConnector;
import com.nissin.smartcitypro.bean.NewsInfos.NewsTabInfo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;
import android.widget.Toast;

public class TabMenuDetailPager extends BaseMenuDetailPager {


	public String tabTitle;
	private TextView tv;
	private ViewPager mTabNews_viewpager;
	private TabData mTabData;
	private String mUrl;
	private NewsTabInfo mNewsTabInfo;
	private BitmapUtils bitmUtil;
	
	public TabMenuDetailPager(Activity mActivity,NewsTabInfo mNewsTabInfo) {
		super(mActivity);
		this.mNewsTabInfo = mNewsTabInfo;
		mUrl = GlobelConnector.GLOBEL_SERVER + mNewsTabInfo.url;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.tab_news_detail, null);
		mTabNews_viewpager = (ViewPager) view.findViewById(R.id.vp_tab_news);
		return view;
	}
	
	@Override
	public void initData() {
		getDataFromService();
	}
	
	private void getDataFromService() {
		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.GET, mUrl, new RequestCallBack<String>() {

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				String result = responseInfo.result;
				parseData(result);
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(mActivity, msg, 0).show();
				error.printStackTrace();
			}
		
		});
	}

	protected void parseData(String result) {
		Gson gson = new Gson();
		mTabData = gson.fromJson(result, TabData.class);
		mTabNews_viewpager.setAdapter(new TabNewsAdapter());
	}

	//tab_news_viewpager 
	class TabNewsAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return mTabData.data.topnews.size();
		}

		
		
		public TabNewsAdapter() {
			super();
			bitmUtil = new BitmapUtils(mActivity);
			//默认加载时，图片
			bitmUtil.configDefaultLoadingImage(R.drawable.topnews_item_default);
		}



		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView image = new ImageView(mActivity);
			image.setImageResource(R.drawable.topnews_item_default);
			image.setScaleType(ScaleType.FIT_XY);
			TopNewsData topNewsData = mTabData.data.topnews.get(position);
			String newUrl = topNewsData.topimage.replace("10.0.2.2", "169.254.65.1");
			bitmUtil.display(image, newUrl);
			container.addView(image);
			return image;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}
		
	}

}
