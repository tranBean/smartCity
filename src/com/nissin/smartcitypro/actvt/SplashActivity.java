package com.nissin.smartcitypro.actvt;

import com.nissin.smartcitypro.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

public class SplashActivity extends Activity {

	private RelativeLayout root;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		initView();
		reflashView();

	}

	private void initView() {
		root = (RelativeLayout) findViewById(R.id.rl_layout);
	}

	private void reflashView() {
		startAnim();
	}

	// 闪屏集合动画
	public void startAnim() {
		AnimationSet aniSet = new AnimationSet(false);
		// 旋转动画
		RotateAnimation rotateAni = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		// 缩放
		ScaleAnimation scaleAni = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
				Animation.RELATIVE_TO_SELF, 0.5f);
		// 渐变
		AlphaAnimation alphaAni = new AlphaAnimation(0, 1);
		aniSet.addAnimation(rotateAni);
		aniSet.addAnimation(scaleAni);
		aniSet.addAnimation(alphaAni);
		aniSet.setDuration(1000);
		aniSet.setFillAfter(true);
		
		aniSet.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				SharedPreferences sp = getSharedPreferences("config", MODE_PRIVATE);
				boolean is_user_guided = sp.getBoolean("is_user_guided", false);
				if(!is_user_guided)
				{
					startActivity(new Intent(SplashActivity.this, GuideActivity.class));
				}else
				{
					startActivity(new Intent(SplashActivity.this, MainActivity.class));
				}
				finish();
			}
		});
		root.startAnimation(aniSet);
	}
}
