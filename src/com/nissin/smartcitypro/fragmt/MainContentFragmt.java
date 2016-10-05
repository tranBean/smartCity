package com.nissin.smartcitypro.fragmt;

import com.nissin.smartcitypro.R;

import android.view.View;

public class MainContentFragmt extends BaseFragment {

	@Override
	protected View initView() {
		View view = View.inflate(mActivity, R.layout.fragmt_main_content, null);
		return view;
	}

}
