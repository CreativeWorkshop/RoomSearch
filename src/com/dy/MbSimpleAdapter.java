package com.dy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MbSimpleAdapter extends SimpleAdapter {
	public List<String> mChecked;
	private List<? extends Map<String, ?>> mData;
	private LayoutInflater mInflater;
	public String[] aa = new String[100];
	int a = 0;
	HashMap<Integer, View> map = new HashMap<Integer, View>();
	Context context = null;
	int index = 0;

	public MbSimpleAdapter(Context context,
			List<? extends Map<String, ?>> data, int resource, String[] from,
			int[] to) {
		super(context, data, resource, from, to);
		mChecked = new ArrayList<String>();

		mData = data;
		setmInflater((LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		this.context = context;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final int p = position;

		View view;
		final GwcViewHolder holder =new GwcViewHolder();

		if (map.get(position) == null) {
			System.out.println("contextcontext  " + context);
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = mInflater.inflate(R.layout.mbmessage_list, null);
			holder.no = (TextView) view.findViewById(R.id.no);
			holder.state = (TextView) view.findViewById(R.id.state);
			System.out.println("pppppppppppppppppp   " + p);
			map.put(position, view);
			view.setTag(holder);
		} else {
			view = map.get(position);
			//holder = (GwcViewHolder) view.getTag();
		}

		// holder.button.
		holder.no.setText(mData.get(position).get("no").toString());
		holder.state.setText(mData.get(position).get("state").toString());
		index++;
		System.out.println("indexindexindexindexindexindexindex   " + index);
		return view;
	}

	static class GwcViewHolder {
		TextView no;
		TextView state;
	}

	/**
	 * 添加这个方法来处理Bitmap类型参数
	 * 
	 * @param v
	 * @param bitmap
	 */
	public void setViewImage(ImageView v, Bitmap bitmap) {
		v.setImageBitmap(bitmap);
	}

	public LayoutInflater getmInflater() {
		return mInflater;
	}

	public void setmInflater(LayoutInflater mInflater) {
		this.mInflater = mInflater;
	}
}
