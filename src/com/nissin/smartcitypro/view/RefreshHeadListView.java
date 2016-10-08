package com.nissin.smartcitypro.view;

import com.nissin.smartcitypro.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class RefreshHeadListView extends ListView {

	public RefreshHeadListView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initRefreshHead(context);
	}

	public RefreshHeadListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initRefreshHead(context);
	}

	public RefreshHeadListView(Context context) {
		super(context);
		initRefreshHead(context);
	}

	private void initRefreshHead(Context context) {
		View headView = View.inflate(context, R.layout.refresh_header, null);
		this.addHeaderView(headView);
		//以下两行代码，看不懂
		headView.measure(0, 0);
		headView.setPadding(0, -headView.getMeasuredHeight(), 0, 0);
	}
}
