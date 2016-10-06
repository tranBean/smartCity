package com.nissin.smartcitypro.fragmt;

import java.util.ArrayList;
import java.util.List;

import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.base.BasePager;
import com.nissin.smartcitypro.base.impl.HomePagerImpl;
import com.nissin.smartcitypro.base.impl.NewsPagerImpl;
import com.nissin.smartcitypro.base.impl.SettingPagerImpl;
import com.nissin.smartcitypro.base.impl.SmartPagerImpl;
import com.nissin.smartcitypro.base.impl.govmtPagerImpl;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainContentFragmt extends BaseFragment {

	private RadioGroup rg_bottom;
	public List<BasePager> mBaseList = new ArrayList<BasePager>();
	private ViewPager vp_body;
	@Override
	protected View initView() {
		View view = View.inflate(mActivity, R.layout.fragmt_main_content, null);
		rg_bottom = (RadioGroup) view.findViewById(R.id.rg_bottom);
		vp_body = (ViewPager) view.findViewById(R.id.vp_main_content);
		return view;
	}

	@Override
	public void initData() {
		mBaseList.add(new HomePagerImpl(mActivity));
		mBaseList.add(new NewsPagerImpl(mActivity));
		mBaseList.add(new SmartPagerImpl(mActivity));
		mBaseList.add(new govmtPagerImpl(mActivity));
		mBaseList.add(new SettingPagerImpl(mActivity));
		vp_body.setAdapter(new MyBaseViewPager());
		rg_bottom.check(R.id.rbt_home);//默认勾选首页
		
		rg_bottom.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.rbt_home:
					vp_body.setCurrentItem(0, false);
					break;
				case R.id.rbt_news:
					vp_body.setCurrentItem(1, false);
					break;
				case R.id.rbt_smart:
					vp_body.setCurrentItem(2, false);
					break;
				case R.id.rbt_grov:
					vp_body.setCurrentItem(3, false);
					break;
				case R.id.rbt_setting:
					vp_body.setCurrentItem(4, false);
					break;

				default:
					break;
				}
			}
		});
		//设置
		vp_body.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				mBaseList.get(position).initData();
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int state) {
				// TODO Auto-generated method stub
				
			}
		});
		//手动加载首页
		mBaseList.get(0).initData();
		super.initData();
	}
	//5大viewpager子页面adapter
	class MyBaseViewPager extends PagerAdapter
	{

		@Override
		public int getCount() {
			return mBaseList.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == object;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			BasePager pagerImpl = mBaseList.get(position);
			container.addView(pagerImpl.mBaseView);
			//pagerImpl.initData();//初始化数据  不要放在此处因为会预加载
			return mBaseList.get(position).mBaseView;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

}
