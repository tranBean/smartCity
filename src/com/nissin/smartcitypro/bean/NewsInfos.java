package com.nissin.smartcitypro.bean;

import java.util.ArrayList;

public class NewsInfos {

	public int retcode;
	public ArrayList<NewsMenuInfo> data;

	// 侧边栏数据对象
	public class NewsMenuInfo {
		public String id;
		public String title;
		public int type;
		public String url;

		public ArrayList<NewsTabInfo> children;

		@Override
		public String toString() {
			return "NewsMenuData [title=" + title + ", children=" + children + "]";
		}
	}

	// 新闻页面下11个子页签的数据对象
	public class NewsTabInfo {
		public String id;
		public String title;
		public int type;
		public String url;

		@Override
		public String toString() {
			return "NewsTabData [title=" + title + "]";
		}
	}

	@Override
	public String toString() {
		return "NewsData [data=" + data + "]";
	}
}
