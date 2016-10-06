package com.nissin.smartcitypro.actvt;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.nissin.smartcitypro.R;
import com.nissin.smartcitypro.fragmt.LeftMenuFragmt;
import com.nissin.smartcitypro.fragmt.MainContentFragmt;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends SlidingFragmentActivity {

	private static String MAIN_CONT_FRAGMT = "main_cont_fragmt";
	private String LEFT_MENU_FRAGMT = "left_menu_fragmt";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setBehindContentView(R.layout.sliding_left);//设置侧边栏
		
		SlidingMenu slid = getSlidingMenu();//获取侧边栏对象
		slid.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置全屏触摸
		slid.setBehindOffset(250);//设置预留屏幕宽度
		
		initFragment();
	}
	
	//初始化fragment
	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction beginTransaction = fm.beginTransaction();
		beginTransaction.replace(R.id.main_content, new MainContentFragmt(), MAIN_CONT_FRAGMT );
		beginTransaction.replace(R.id.left_slid_menu, new LeftMenuFragmt(), LEFT_MENU_FRAGMT);
		beginTransaction.commit();
		/*fm.findFragmentByTag(MAIN_CONT_FRAGMT);*/
	}
	//获取侧边栏对象
	public LeftMenuFragmt getLeftMenuFragtObj()
	{	FragmentManager fm = getSupportFragmentManager();
		return (LeftMenuFragmt) fm.findFragmentByTag(LEFT_MENU_FRAGMT);
	}
	
	//获取主内容页面对象
	public MainContentFragmt getMainContentFragtObj()
	{	FragmentManager fm = getSupportFragmentManager();
		return (MainContentFragmt) fm.findFragmentByTag(MAIN_CONT_FRAGMT);
	}
}

