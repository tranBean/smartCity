package com.nissin.smartcitypro.base.impl;

import java.util.ArrayList;
import java.util.List;

import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.base.BaseMenuDetailPager;
import com.nissin.smartcitypro.bean.NewsInfos.NewsTabInfo;

import android.app.Activity;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
/**
 * 菜单详情页-----新闻
 * @author Administrator
 *
 */
public class NewsMenuDetailPagerImpl extends BaseMenuDetailPager {

	private ViewPager mTabMenuViewpager;
	private List<TabMenuDetailPager> tabMenus;
	private ArrayList<NewsTabInfo> newsTabInfos;
	
	public NewsMenuDetailPagerImpl(Activity mActivity,ArrayList<NewsTabInfo> newsTabInfos) {
		super(mActivity);
		this.newsTabInfos = newsTabInfos;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.tab_mune_viewpager, null);
		mTabMenuViewpager = (ViewPager) view.findViewById(R.id.vp_tab_menu);
		/*TextView tv = new TextView(mActivity);
		tv.setText("详情页面-----新闻");
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(22);
		tv.setTextColor(Color.RED);*/
		return view;
	}
	
	@Override
	public void initData() {//注意这个方法的调用   时机！！！！！！
		tabMenus = new ArrayList<TabMenuDetailPager>();
		for(int i=0;i<newsTabInfos.size();i++)
		{
			TabMenuDetailPager tabmenu = new TabMenuDetailPager(mActivity);
			tabmenu.tabTitle = newsTabInfos.get(i).title;
			tabMenus.add(tabmenu);
		}
		mTabMenuViewpager.setAdapter(new TabMenuVpAdapter());
	}
	//tab菜单
	class TabMenuVpAdapter extends PagerAdapter
	{

		@Override
		public int getCount() {
			return tabMenus.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(tabMenus.get(position).mRootView);
			tabMenus.get(position).initData();//暂时先放这初始化数据
			return tabMenus.get(position).mRootView;
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
