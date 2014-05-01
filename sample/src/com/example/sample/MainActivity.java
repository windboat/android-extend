package com.example.sample;

import com.guo.android_extend.widget.HorizontalListView;
import com.guo.android_extend.widget.HorizontalListView.OnItemScrollListener;
import com.guo.android_extend.widget.ScalableImageView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity" ;

	/**
	 * scale percent.
	 */
	float percent_add = 0.5f;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		HorizontalListView hv = (HorizontalListView) this.findViewById(R.id.listView1);
		hv.setAdapter(new ViewListAdapter(this));
		hv.setOnItemScrollListener(new HScrollListener());
	}

	private void scale(View v, float percent) {
		Holder holder = (Holder) v.getTag();
		holder.siv.setScale(percent, percent);
		holder.siv.invalidate();
	}
	
	class Holder {
		ScalableImageView siv;
		TextView tv;
	}
	
	class ViewListAdapter extends BaseAdapter {
		Context mContext;
		LayoutInflater mLInflater;
		String[] mNames = {
				"Camera",
				"Test1","Test6","Test6",
				"Test2","Test6","Test6",
				"Test3","Test6","Test6",
				"Test4","Test6","Test6",
				"Test5","Test6","Test6",
				"Test6","Test6","Test6",
		};
		
		public ViewListAdapter(Context c) {
			// TODO Auto-generated constructor stub
			mContext = c;
			mLInflater = LayoutInflater.from(mContext);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return mNames.length;
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			Holder holder = null;
			if (convertView != null) {
				holder = (Holder) convertView.getTag();
			} else {
				convertView = mLInflater.inflate(R.layout.item_sample, null);
				holder = new Holder();
				holder.siv = (ScalableImageView) convertView.findViewById(R.id.imageView1);
				holder.tv = (TextView) convertView.findViewById(R.id.textView1);
				convertView.setTag(holder);
			}
			
			holder.tv.setText(mNames[ position ]);
			
			scale(convertView, 1f - percent_add);
			
			return convertView;
		}
		
	}
	
	class HScrollListener implements OnItemScrollListener {

		@Override
		public void OnScrollCenter(AdapterView<ListAdapter> adp, View v,
				int pos, float percent) {
			// TODO Auto-generated method stub
			Log.i(TAG, "OnScrollCenter pos=" + pos + ", percent=" + percent);
			float scale = (1f - percent_add) + percent_add * percent;
			scale(v, scale);
			if (adp.getChildCount() > pos + 1) {
				scale(adp.getChildAt(pos + 1), 1f + percent_add - scale);
			}
			if (pos > 0) {
				scale(adp.getChildAt(pos - 1), 1f - percent_add);
			}
		}

		@Override
		public void OnScrollStart(AdapterView<ListAdapter> adp) {
			// TODO Auto-generated method stub
			Log.i(TAG, "OnScrollStart");
		}

		@Override
		public void OnScrollEnd(AdapterView<ListAdapter> adp, int pos) {
			// TODO Auto-generated method stub
			Log.i(TAG, "OnScrollEnd pos=" + pos);
		}

		@Override
		public boolean OnDraging(AdapterView<ListAdapter> adp, float dx,
				float dy) {
			// TODO Auto-generated method stub
			Log.i(TAG, "OnDraging dx=" + dx + ", dy=" + dy);
			return false;
		}

		@Override
		public boolean OnDragingOver(AdapterView<ListAdapter> adp) {
			// TODO Auto-generated method stub
			Log.i(TAG, "OnDragingOver");
			return true;
		}
		
	}
}