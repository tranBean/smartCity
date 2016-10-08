package com.nissin.smartcitypro.base.leftimpl;

import java.util.ArrayList;
import java.util.List;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.actvt.MainActivity;
import com.nissin.smartcitypro.base.BaseMenuDetailPager;
import com.nissin.smartcitypro.base.impl.TabMenuDetailPager;
import com.nissin.smartcitypro.bean.NewsInfos.NewsTabInfo;
import com.viewpagerindicator.TabPageIndicator;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * 菜单详情页-----新闻
 * 
 * @author Administrator
 *
 */
public class NewsMenuDetailPagerImpl extends BaseMenuDetailPager implements OnPageChangeListener{

	private ViewPager mTabMenuViewpager;
	private List<TabMenuDetailPager> tabMenus;
	private ArrayList<NewsTabInfo> newsTabInfos;
	private TabPageIndicator indicator;

	public NewsMenuDetailPagerImpl(Activity mActivity, ArrayList<NewsTabInfo> newsTabInfos) {
		super(mActivity);
		this.newsTabInfos = newsTabInfos;
	}

	@Override
	public View initView() {
		View view = View.inflate(mActivity, R.layout.tab_mune_viewpager, null);
		mTabMenuViewpager = (ViewPager) view.findViewById(R.id.vp_tab_menu);
		indicator = (TabPageIndicator) view.findViewById(R.id.indicator);
		// 跳到下一个tab
		ImageButton ib2nextTab = (ImageButton) view.findViewById(R.id.ib_tonext);
		ib2nextTab.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int currentItem = mTabMenuViewpager.getCurrentItem();
				mTabMenuViewpager.setCurrentItem(++currentItem);
			}
		});
		indicator.setOnPageChangeListener(this);
		return view;
	}

	@Override
	public void initData() {// 注意这个方法的调用 时机！！！！！！
		tabMenus = new ArrayList<TabMenuDetailPager>();
		for (int i = 0; i < newsTabInfos.size(); i++) {
			TabMenuDetailPager tabmenu = new TabMenuDetailPager(mActivity, newsTabInfos.get(i));
			tabmenu.tabTitle = newsTabInfos.get(i).title;
			tabMenus.add(tabmenu);
		}
		mTabMenuViewpager.setAdapter(new TabMenuVpAdapter());
		// 设置tab向导 必须在viewpager设置adapter后才可以调用
		indicator.setViewPager(mTabMenuViewpager);
	}

	// tab菜单
	class TabMenuVpAdapter extends PagerAdapter {

		@Override
		public CharSequence getPageTitle(int position) {
			return newsTabInfos.get(position).title;
		}

		@Override
		public int getCount() {
			return tabMenus.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(tabMenus.get(position).mRootView);
			tabMenus.get(position).initData();// 暂时先放这初始化数据
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

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		System.out.println("监听到了吗");
		MainActivity mainActivity = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		if(position == 0)
		{//如果在北京位置上， 希望可以拉出侧边栏
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		}else
		{//不希望拉出侧边栏
			slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}
}
