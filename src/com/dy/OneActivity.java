package com.dy;

import java.net.URLEncoder;

import com.dy.util.HttpUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class OneActivity extends Activity {
	/** Called when the activity is first created. */
	private Button button1;
	private Button button2;
	private Spinner spinner1;
	private ArrayAdapter<String> adapter1;
	private EditText text1;
	String state = "";
	int number = 0;
	private String[] m1 = {"请选择","占用","空闲"};

	// 声明签到、离开按钮
	private Button signBn,leaveBn;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		setTitle("查询");
		// 通过findViewById方法实例化组件
		signBn = (Button)findViewById(R.id.sign);
		leaveBn = (Button)findViewById(R.id.leave);
		button1 = (Button) this.findViewById(R.id.Button1);
		button1.setOnClickListener(myListener1);
		

		button2 = (Button) this.findViewById(R.id.Button2);
		button2.setOnClickListener(myListener2);

		text1 = (EditText) findViewById(R.id.Text1);

		// -------------------------spinner1
		spinner1 = (Spinner) findViewById(R.id.Spinner01);
		// 将可选内容与ArrayAdapter连接起来
		adapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, m1);

		// 设置下拉列表的风格
		adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// 将adapter 添加到spinner中
		spinner1.setAdapter(adapter1);

		// 添加事件Spinner事件监听
		spinner1.setOnItemSelectedListener(new SpinnerSelectedListener1());

		// 设置默认值
		spinner1.setVisibility(View.VISIBLE);


		signBn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "SignServlet?no="
							+ URLEncoder.encode(
									URLEncoder.encode(text1.getText().toString(), "UTF-8"), "UTF-8");
					// 查询返回结果
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(result.equals("0")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext(), "签到成功", 1).show();
					}else{
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext(),"签到失败", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		leaveBn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "leaveServlet?no="
							+ URLEncoder.encode(
									URLEncoder.encode(text1.getText().toString(), "UTF-8"), "UTF-8");
					// 查询返回结果
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(result.equals("0")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext(), "学习愉快 （^_^）", 1).show();
					}else{
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext()," 出错了    T_T ", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
	}
	
	
	// 计算
	private OnClickListener myListener1 = new Button.OnClickListener() {

		public void onClick(View v) {
			String no = text1.getText().toString();
			Intent intent = new Intent();

			intent.setClass(OneActivity.this, MbListActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("state", state);
			bundle.putString("no", no);
			intent.putExtras(bundle);
			startActivity(intent);

		}

	};

	// 使用数组形式操作
	class SpinnerSelectedListener1 implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			state = m1[arg2];
		}

		public void onNothingSelected(AdapterView<?> arg0) {
		}
	}

	// 清空
	private OnClickListener myListener2 = new Button.OnClickListener() {
		public void onClick(View v) {
			text1.setText("");
		}

	};
	
}