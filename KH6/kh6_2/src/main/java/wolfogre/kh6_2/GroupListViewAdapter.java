package wolfogre.kh6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/18/2016.
 */
public class GroupListViewAdapter extends BaseAdapter {
	int groupNumber;
	boolean[] isDownList;
	LayoutInflater layoutInflater;

	public GroupListViewAdapter(Context context, String gameName){
		SharedPreferences spGroup = context.getSharedPreferences(context.getString(R.string.name_group_number), Context.MODE_PRIVATE);
		groupNumber = spGroup.getInt(gameName, 0);
		isDownList = new boolean[groupNumber];
		for(int i = 0; i < isDownList.length; ++i)
			isDownList[i] = false;
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return groupNumber;
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_group_list_view, null);
			viewHolder.tvGroupName = (TextView) convertView.findViewById(R.id.tvGroupName);
			viewHolder.ivDown = (ImageView) convertView.findViewById(R.id.ivDown);
			viewHolder.lvPeople = (ListView)convertView.findViewById(R.id.lvPeople);
			viewHolder.ibAddPeople = (ImageButton)convertView.findViewById(R.id.ibAddPeople);
			viewHolder.rlPeople = (RelativeLayout)convertView.findViewById(R.id.rlPeople);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.tvGroupName.setText("小组" + (position + 1));

		final RelativeLayout rlPeople = viewHolder.rlPeople;
		final View finalConvertView = convertView;
		final int finalPosition = position;
		viewHolder.ivDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isDownList[finalPosition]) {
					isDownList[finalPosition] = false;
					rlPeople.setVisibility(View.INVISIBLE);
					((ImageView)v).setImageResource(R.drawable.ic_keyboard_arrow_left_black_48dp);
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
					finalConvertView.setLayoutParams(lp);
				} else {
					isDownList[finalPosition] = true;
					rlPeople.setVisibility(View.VISIBLE);
					((ImageView)v).setImageResource(R.drawable.ic_keyboard_arrow_down_black_48dp);
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 800);
					finalConvertView.setLayoutParams(lp);
				}
				finalConvertView.postInvalidate();
			}
		});

		if (isDownList[position]){
			rlPeople.setVisibility(View.VISIBLE);
			viewHolder.ivDown.setImageResource(R.drawable.ic_keyboard_arrow_down_black_48dp);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 800);
			finalConvertView.setLayoutParams(lp);
		} else {
			rlPeople.setVisibility(View.INVISIBLE);
			viewHolder.ivDown.setImageResource(R.drawable.ic_keyboard_arrow_left_black_48dp);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
			finalConvertView.setLayoutParams(lp);
		}
		return convertView;
	}

	class ViewHolder{
		TextView tvGroupName;
		ImageView ivDown;
		ListView lvPeople;
		ImageButton ibAddPeople;
		RelativeLayout rlPeople;
	}
}
