package com.nissin.smartcitypro.actvt;

import java.util.ArrayList;
import java.util.List;

import com.nissin.smartcitypro.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class GuideActivity extends Activity {

	private int[] imagRes;
	private List<ImageView> mImagvList;
	private ViewPager vpGuide;
	private GuideViewPagerAdapter vpGuidAdp;
	private LinearLayout ll_point_layout;
	private LinearLayout.LayoutParams pointParams;
	private View redpoint;
	private int mPointDisWidth;
	private Button btnGuide;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		imagRes = new int[] { R.drawable.guide_1, R.drawable.guide_2, R.drawable.guide_3 };
		mImagvList = new ArrayList<ImageView>();
		initView();
		reflashView();
	}

	// 初始化view
	private void initView() {
		vpGuide = (ViewPager) findViewById(R.id.vp_guide);
		ll_point_layout = (LinearLayout) findViewById(R.id.ll_point);
		redpoint = findViewById(R.id.red_point);
		btnGuide = (Button) findViewById(R.id.btn_guide);
		vpGuidAdp = new GuideViewPagerAdapter();
	}

	// 刷新view
	private void reflashView() {
		for (int i = 0; i < imagRes.length; i++) {
			ImageView imageV = new ImageView(this);
			imageV.setBackgroundResource(imagRes[i]);
			mImagvList.add(imageV);
		}
		vpGuide.setAdapter(vpGuidAdp);

		for (int i = 0; i < imagRes.length; i++) {
			View grayView = new View(this);
			grayView.setBackgroundResource(R.drawable.sha_point_gray_guide);
			pointParams = new LinearLayout.LayoutParams(10, 10);
			if (imagRes.length > 0) {
				pointParams.leftMargin = 10;
			}
			grayView.setLayoutParams(pointParams);
			ll_point_layout.addView(grayView);
		}

		// 通过视图树观察者，拿到两点灰点的直线距离
		ll_point_layout.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

			@Override
			public void onGlobalLayout() {
				ll_point_layout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
				mPointDisWidth = ll_point_layout.getChildAt(1).getLeft() - ll_point_layout.getChildAt(0).getLeft();
			}

		});

		// 设置页面改变监听
		vpGuide.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
				RelativeLayout.LayoutParams layoutParams = (android.widget.RelativeLayout.LayoutParams) redpoint
						.getLayoutParams();
				layoutParams.leftMargin = (int) (mPointDisWidth * positionOffset + position * mPointDisWidth);
				redpoint.setLayoutParams(layoutParams);
			}

			@Override
			public void onPageSelected(int position) {
				if(position == mImagvList.size() - 1)
				{
					btnGuide.setVisibility(View.VISIBLE);
				}else
				{
					btnGuide.setVisibility(View.INVISIBLE);
				}
			}

			@Override
			public void onPageScrollStateChanged(int state) {

			}

		});
		//点击按钮进入主页面
		btnGuide.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//只进入一次新手引导页
				SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
				sp.edit().putBoolean("is_user_guided", true).commit();
				startActivity(new Intent(GuideActivity.this, MainActivity.class));
				finish();
			}
		});
	}

	// 引导页的viewpager_Adapter
	class GuideViewPagerAdapter extends PagerAdapter {

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
			// super.destroyItem(container, position, object);
		}

		@Override
		public int getCount() {
			return mImagvList.size();
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mImagvList.get(position));
			return mImagvList.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

	}
}
