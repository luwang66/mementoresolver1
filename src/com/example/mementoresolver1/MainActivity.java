package com.example.mementoresolver1;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button chooseDate, add, show;
	private EditText date, subject, body;
	private ListView result;
	private LinearLayout title;
	private ContentResolver contentResolver;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		contentResolver = getContentResolver();
		chooseDate = (Button) findViewById(R.id.chooseDate);
		add = (Button) findViewById(R.id.add);
		show = (Button) findViewById(R.id.show);
		date = (EditText) findViewById(R.id.date);
		subject = (EditText) findViewById(R.id.subject);
		body = (EditText) findViewById(R.id.body);
		result = (ListView) findViewById(R.id.result);
		title = (LinearLayout) findViewById(R.id.title);
		title.setVisibility(View.INVISIBLE);
		chooseDate.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();// 获取当前日期
				new DatePickerDialog(MainActivity.this,// 日期选择器对话框
						new DatePickerDialog.OnDateSetListener() {// 日期改变监听器
							public void onDateSet(DatePicker view, int year,
									int month, int day) {
								date.setText(year + "-" + (month + 1) + "-"
										+ day);
							}
						}, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c
								.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ContentValues values = new ContentValues();
				values.put(Mementos.Memento.SUBJECT, subject.getText()
						.toString());
				values.put(Mementos.Memento.BODY, body.getText().toString());
				values.put(Mementos.Memento.DATE, date.getText().toString());
				contentResolver.insert(Mementos.Memento.MEMENTOS_CONTENT_URI,
						values);
				Toast.makeText(MainActivity.this, "添加生词成功！", Toast.LENGTH_LONG)
						.show();
			}
		});
		show.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Cursor cursor = contentResolver.query(
						Mementos.Memento.MEMENTOS_CONTENT_URI, null, null,
						null, null);
				System.out.println(cursor);
				SimpleCursorAdapter resultAdapter = new SimpleCursorAdapter(
						MainActivity.this, R.layout.result, cursor,
						new String[] { Mementos.Memento._ID,
								Mementos.Memento.SUBJECT,
								Mementos.Memento.BODY, Mementos.Memento.DATE },
						new int[] { R.id.memento_num, R.id.memento_subject,
								R.id.memento_body, R.id.memento_date });
				result.setAdapter(resultAdapter);
			}
		});
	}
}