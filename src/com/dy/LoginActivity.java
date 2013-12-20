package com.dy;

import java.net.URLEncoder;
import com.dy.util.HttpUtil;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	// ������¼��ȡ����ť
	private Button cancelBtn,loginBtn,exitBtn,registerBtn;
	// �����û��������������
	private EditText userEditText,pwdEditText;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ���ñ���
		setTitle("����ϵͳ-��¼");
		// ���õ�ǰActivity���沼��
		setContentView(R.layout.login_system);
		// ͨ��findViewById����ʵ�������
		cancelBtn = (Button)findViewById(R.id.cancelButton);
		// ͨ��findViewById����ʵ�������
		loginBtn = (Button)findViewById(R.id.loginButton);
		exitBtn = (Button)findViewById(R.id.exitButton);
		registerBtn = ((Button)findViewById(R.id.registerButton));
		// ͨ��findViewById����ʵ�������
		userEditText = (EditText)findViewById(R.id.userEditText);
		// ͨ��findViewById����ʵ�������
		pwdEditText = (EditText)findViewById(R.id.pwdEditText);
		
		cancelBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				userEditText.setText("");
				pwdEditText.setText("");
			}
		});
		
		exitBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				System.exit(0);   
			}
		});
		
		
		loginBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "LoginServlet?userName="
							+ URLEncoder.encode(
									URLEncoder.encode(userEditText.getText().toString(), "UTF-8"), "UTF-8")+"&password="
									+ URLEncoder.encode(
									URLEncoder.encode(pwdEditText.getText().toString(), "UTF-8"), "UTF-8");
					// ��ѯ���ؽ��
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(!result.equals("0")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext(), "��¼�ɹ�", 1).show();
						Intent intent = new Intent();
						intent.setClass(LoginActivity.this,
								OneActivity.class);
						startActivity(intent);
						
					}else{
						Toast.makeText(getApplicationContext(), "�û������������", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		//****************************************************************************************
		registerBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				try {
					String url = HttpUtil.BASE_URL
							+ "RegisterServlet?userName="
							+ URLEncoder.encode(
									URLEncoder.encode(userEditText.getText().toString(), "UTF-8"), "UTF-8")+"&password="
									+ URLEncoder.encode(
									URLEncoder.encode(pwdEditText.getText().toString(), "UTF-8"), "UTF-8");
					// ��ѯ���ؽ��
					String result = HttpUtil.queryStringForPost(url);
					System.out.println("=========================  "+result);
					if(!result.equals("1")){
						System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
						Toast.makeText(getApplicationContext(), "�û����Ѵ���", 1).show();
						
					}else{
						Toast.makeText(getApplicationContext(), "ע��ɹ�", 1).show();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		//********************************************
	}
	public Button getRegisterBtn() {
		return registerBtn;
	}
	public void setRegisterBtn(Button registerBtn) {
		this.registerBtn = registerBtn;
	}
}