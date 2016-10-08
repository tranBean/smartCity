package com.nissin.smartcitypro.base.impl;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.base.BaseMenuDetailPager;
import com.nissin.smartcitypro.bean.NewsInfos.NewsTabInfo;
import com.nissin.smartcitypro.bean.TabData;
import com.nissin.smartcitypro.bean.TabData.TabNewsData;
import com.nissin.smartcitypro.bean.TabData.TopNewsData;
import com.nissin.smartcitypro.tools.GlobelConnector;
import com.viewpagerindicator.CirclePageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TabMenuDetailPager extends BaseMenuDetailPager implements OnPageChangeListener {

	public String tabTitle;
	private TextView tv;
	private ViewPager mTabNews_viewpager;
	private TabData mTabData;
	private String mUrl;
	private NewsTabInfo mNewsTabInfo;
	private BitmapUtils bitmUtil;
	private TextView tv_title;
	private ArrayList<TopNewsData> mTopNewsDatalist;
	private CirclePageIndicator mTopNewsIndicator;
	private ArrayList<TabNewsData> newsList;
	private ListView lvList;
	private BitmapUtils bitmap;

	public TabMenuDetailPager(Activity mActivity, NewsTabInfo mNewsTabInfo) {
		super(mActivity);
		this.mNewsTabInfo = mNewsTabInfo;
		mUrl = GlobelConnector.GLOBEL_SERVER + mNewsTabInfo.url;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.tab_news_detail, null);
		
		View headView = View.inflate(mActivity, R.layout.head_view, null);
		
		mTabNews_viewpager = (ViewPager) headView.findViewById(R.id.vp_tab_news);//----------1.
		lvList = (ListView) view.findViewById(R.id.lv_tab_news);
		//添加头布局
		lvList.addHeaderView(headView);
		
		tv_title = (TextView) headView.findViewById(R.id.tv_title);//----------2
		
		mTopNewsIndicator = (CirclePageIndicator) headView.findViewById(R.id.indicator);//----------3
		mTopNewsIndicator.setOnPageChangeListener(this);
		
		
		
		return view;
	}

	// 新闻列表listview
	class NewsAdapter extends BaseAdapter {
		
		public NewsAdapter() {
			super();
			bitmap = new BitmapUtils(mActivity);
			bitmap.configDefaultLoadingImage(R.drawable.pic_item_list_default);
		}

		private ImageView iv_pic;
		private TextView tv_title;
		private TextView tv_date;

		@Override
		public int getCount() {
			return newsList.size();
		}

		@Override
		public Object getItem(int position) {
			return newsList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = null;
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				view = View.inflate(mActivity, R.layout.item_list_news, null);
				holder.iv_pic = (ImageView) view.findViewById(R.id.iv_pic);
				holder.tv_title = (TextView) view.findViewById(R.id.tv_title);
				holder.tv_date = (TextView) view.findViewById(R.id.tv_date);
				convertView = view;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			bitmap.display(holder.iv_pic, newsList.get(position).listimage.replace("10.0.2.2", "169.254.65.1"));
			holder.tv_date.setText(newsList.get(position).pubdate);
			holder.tv_title.setText(newsList.get(position).title);
			return convertView;
		}

		class ViewHolder {
			ImageView iv_pic;
			TextView tv_title;
			TextView tv_date;
		}

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
		newsList = mTabData.data.news;
		lvList.setAdapter(new NewsAdapter());
		mTopNewsDatalist = mTabData.data.topnews;
		tv_title.setText(mTopNewsDatalist.get(0).title);
		mTopNewsIndicator.setViewPager(mTabNews_viewpager);
	}

	// tab_news_viewpager
	class TabNewsAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mTabData.data.topnews.size();
		}

		public TabNewsAdapter() {
			super();
			bitmUtil = new BitmapUtils(mActivity);
			// 默认加载时，图片
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

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		TopNewsData topNewsData = mTopNewsDatalist.get(position);
		tv_title.setText(topNewsData.title);
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

}
