package wolfogre.kh6_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import wolfogre.kh8_1.*;


/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/18/2016.
 */
public class GroupListViewAdapter extends BaseAdapter {
	int groupNumber;
	boolean[] isDownList;
	LayoutInflater layoutInflater;
	Activity activity;
	String gameName;

	public GroupListViewAdapter(Activity activity, String gameName){
		groupNumber = GameInfoByJson.getGroupNumber(gameName);
		isDownList = new boolean[groupNumber];
		for(int i = 0; i < isDownList.length; ++i)
			isDownList[i] = false;
		layoutInflater = LayoutInflater.from(activity);
		this.activity = activity;
		this.gameName = gameName;
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			viewHolder = new ViewHolder();
			convertView = layoutInflater.inflate(R.layout.item_group_list_view, null);
			viewHolder.tvGroupName = (TextView) convertView.findViewById(R.id.tvGroupName);
			viewHolder.ivDown = (ImageView) convertView.findViewById(R.id.ivDown);
			viewHolder.lvPeople = (ListView)convertView.findViewById(R.id.lvPeople);
			viewHolder.ivAddPeople = (ImageView)convertView.findViewById(R.id.ivAddPeople);
			viewHolder.rlPeople = (RelativeLayout)convertView.findViewById(R.id.rlPeople);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.tvGroupName.setText("小组" + (position + 1));

		final RelativeLayout rlPeople = viewHolder.rlPeople;
		final View finalConvertView = convertView;
		viewHolder.ivDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isDownList[position]) {
					isDownList[position] = false;
					rlPeople.setVisibility(View.INVISIBLE);
					((ImageView)v).setImageResource(R.drawable.ic_keyboard_arrow_left_black_48dp);
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
					finalConvertView.setLayoutParams(lp);
				} else {
					isDownList[position] = true;
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

		viewHolder.ivAddPeople.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AddPeopleDialogFragment().show(activity.getFragmentManager(),
						activity.getIntent().getStringExtra(activity.getString(R.string.intent_key_game_name))
						+ "###" + position);
			}
		});

		viewHolder.lvPeople.setAdapter(new PeopleListViewAdapter(activity, position, gameName));

		return convertView;
	}

	class ViewHolder{
		TextView tvGroupName;
		ImageView ivDown;
		ListView lvPeople;
		ImageView ivAddPeople;
		RelativeLayout rlPeople;
	}
}
