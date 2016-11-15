package com.example.mementoresolver1;

import android.net.Uri;
import android.provider.BaseColumns;

public class Mementos {
	public static final String AUTHORITY = "iet.jxufe.cn.providers.memento";

	public static final class Memento implements BaseColumns {
		public static final String _ID = "_id";//memento_tb����_id�ֶ�
		public static final String SUBJECT = "subject";//memento_tb����subject�ֶ�
		public static final String BODY = "body";//memento_tb����bodyt�ֶ�
		public static final String DATE = "date";//memento_tb����date�ֶ�
		public static final Uri MEMENTOS_CONTENT_URI= Uri.parse("content://"
				+ AUTHORITY + "/mementos");//�ṩ����mementos����URI
		public static final Uri MEMENTO_CONTENT_URI= Uri.parse("content://"
				+ AUTHORITY + "/memento");//�ṩ��������mementoURI
	}
}