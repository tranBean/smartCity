package com.nissin.smartcitypro.fragmt;

import java.util.ArrayList;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.actvt.MainActivity;
import com.nissin.smartcitypro.base.impl.NewsPagerImpl;
import com.nissin.smartcitypro.bean.NewsInfos;
import com.nissin.smartcitypro.bean.NewsInfos.NewsMenuInfo;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class LeftMenuFragmt extends BaseFragment {

	public ListView leftMenuListView;
	private View view;
	public int mPosition;
	//private NewsInfos newsInfos;
	private ArrayList<NewsMenuInfo> newsMenuInfos;
	private LeftMenuListViewAdapter mListViewAdapter;
	@Override
	protected View initView() {
		view = View.inflate(mActivity, R.layout.fragmt_left_menu, null);
		leftMenuListView = (ListView) view.findViewById(R.id.lv_leftMenu);
		mListViewAdapter = new LeftMenuListViewAdapter();
		leftMenuListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				mPosition = position;
				mListViewAdapter.notifyDataSetChanged();
				//在FrameLayout展示详情页界面
				setCurMenuDetailPagerImpl(position);
				slidingToggle();
			}
		
		});
		return view;
	}
	//侧边栏toggle
	protected void slidingToggle() {
		MainActivity mainActivity = (MainActivity) mActivity;
		SlidingMenu slidingMenu = mainActivity.getSlidingMenu();
		slidingMenu.toggle();
	}
	//调用NewsPagerImpl使得在FrameLayout展示详情页界面
	protected void setCurMenuDetailPagerImpl(int position) {
		MainActivity mainActivity = (MainActivity) mActivity;
		MainContentFragmt mainContentFragtObj = mainActivity.getMainContentFragtObj();
		NewsPagerImpl newspager = (NewsPagerImpl) mainContentFragtObj.mBaseList.get(1);
		newspager.setCurMenuDetailPagerImpl(position);
	}

	@Override
	public void initData() {
		super.initData();
	}
	//侧边栏listview adapter
	class LeftMenuListViewAdapter extends BaseAdapter
	{

		@Override
		public int getCount() {
			return newsMenuInfos.size();
		}

		@Override
		public Object getItem(int position) {
			return newsMenuInfos.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LinearLayout llView = (LinearLayout) View.inflate(mActivity, R.layout.item_left_menu, null);
			TextView textView = (TextView) llView.findViewById(R.id.lf_menu_tv_title);
			textView.setText(newsMenuInfos.get(position).title);
			if(mPosition == position)
			{
				textView.setEnabled(true);
			}else
			{
				textView.setEnabled(false);
			}
			return llView;
		}
		
	}
	
	//将数据传递到侧边栏
	public void setDataFromService(NewsInfos newsInfos) {
		//this.newsInfos = newsInfos;
		this.newsMenuInfos = newsInfos.data;
		leftMenuListView.setAdapter(mListViewAdapter);
	}

	
}
