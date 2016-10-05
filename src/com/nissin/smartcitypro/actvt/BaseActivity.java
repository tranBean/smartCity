package com.nissin.smartcitypro.actvt;

import java.util.LinkedList;
import java.util.List;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class BaseActivity extends Activity {

	private ActionBar supActionBar;
	public final static List<BaseActivity> mActivities = new LinkedList<BaseActivity>();
	public List<BaseActivity> copyActivities;
	public static BaseActivity baseActivity;
	
	/*public KillAllReceiver killAllReceiver;
	class KillAllReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
		  finish();
		}

	}*/

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		baseActivity = this;
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		baseActivity = null;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		/*killAllReceiver = new KillAllReceiver();
		IntentFilter killAllFilter = new IntentFilter("com.nissin.googleplay.activity");
		registerReceiver(killAllReceiver, killAllFilter);*/
		init();
		initView();
		//initActionBar();
		
	}

	/*protected void initActionBar() {
		// TODO Auto-generated method stub
		synchronized (mActivities) {
			mActivities.add(this);
		}
		supActionBar = getSupportActionBar();
		supActionBar.setDisplayHomeAsUpEnabled(true);
	}*/

	protected void initView() {
		// TODO Auto-generated method stub

	}

	protected void init() {
		// TODO Auto-generated method stub

	}

	public void killAllAct() {
		synchronized (mActivities) {
			copyActivities = new LinkedList<BaseActivity>(mActivities);
		}
		// List<BaseActivity> mCopyActivities = mActivities;
		for (BaseActivity sonActivity : copyActivities) {
			sonActivity.finish();
		}
		android.os.Process.killProcess(android.os.Process.myPid());
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		synchronized (mActivities) {
			mActivities.remove(this);
		}
	}

}
