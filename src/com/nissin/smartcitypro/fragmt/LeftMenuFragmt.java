package com.nissin.smartcitypro.fragmt;

import com.nissin.smartcitypro.R;

import android.view.View;

public class LeftMenuFragmt extends BaseFragment {

	@Override
	protected View initView() {
		View view = View.inflate(mActivity, R.layout.fragmt_left_menu, null);
		return view;
	}

}
