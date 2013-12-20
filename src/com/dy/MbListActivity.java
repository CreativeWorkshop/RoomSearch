package com.dy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dy.util.HttpUtil;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MbListActivity extends Activity {

	MbSimpleAdapter adapter;
	ListView lv;
	EditText et;
	String id;
	String threadId;
	int index;
	List<Map<String, Object>> list;
	List<Integer> listItemID = new ArrayList<Integer>();
	String state;
	String no;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mblist);
		setTitle("查询--列表");
		 Bundle extras = this.getIntent().getExtras();
		 state= extras.getString("state");
		 no= extras.getString("no");
		setViews();
	}

	private void setViews() {
		lv = (ListView) findViewById(R.id.mb_list_view);
		list = getDatas();
		adapter = new MbSimpleAdapter(this, list, R.layout.mbmessage_list,
				new String[] { "no","state" }, new int[] { R.id.no, R.id.state });
		lv.setAdapter(adapter);


	}
	

	private List<Map<String, Object>> getDatas() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String url="";
		try {
			url = HttpUtil.BASE_URL + "ListServlet?state="+URLEncoder.encode(
					URLEncoder.encode(state, "UTF-8"),
					"UTF-8")+"&no="+URLEncoder.encode(
							URLEncoder.encode(no, "UTF-8"),
							"UTF-8");
		} catch (UnsupportedEncodingException e1) {
			
			e1.printStackTrace();
		}
		// 查询返回结果
		String result = HttpUtil.queryStringForPost(url);
		System.out.println("=========================  " + result);
		String[] results = result.split("@");

		try {
			for (int i = 0; i < results.length; i++) {
				String[] photos = results[i].split(",");

					Map<String, Object> map = new HashMap<String, Object>();
					map.put("no", photos[0]);
					map.put("state", photos[1]);
					list.add(map);
			}
		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "", 1).show();
		}
		return list;
	}

}