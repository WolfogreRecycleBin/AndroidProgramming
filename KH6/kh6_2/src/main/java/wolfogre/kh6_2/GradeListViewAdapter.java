package wolfogre.kh6_2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/19/2016.
 */
public class GradeListViewAdapter extends BaseAdapter {

	String[] peopleList;
	boolean[] isDownList;
	LayoutInflater layoutInflater;
	Activity activity;
	String gameName;
	int groupId;

	public GradeListViewAdapter(Activity activity, String gameName, int groupId){
		peopleList = GameInfoBySharedPreferences.getPeopleNames(gameName, groupId);

		isDownList = new boolean[peopleList.length];
		for(int i = 0; i < isDownList.length; ++i)
			isDownList[i] = false;
		layoutInflater = LayoutInflater.from(activity);
		this.activity = activity;
		this.gameName = gameName;
		this.groupId = groupId;
	}


	@Override
	public int getCount() {
		return peopleList.length;
	}

	@Override
	public Object getItem(int position) {
		return peopleList[position];
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
			convertView = layoutInflater.inflate(R.layout.item_grade_list_view, null);
			viewHolder.tvPeopleName = (TextView) convertView.findViewById(R.id.tvPeopleName);
			viewHolder.ivDown = (ImageView) convertView.findViewById(R.id.ivDown);
			viewHolder.lvDetailGrade = (ListView)convertView.findViewById(R.id.lvDetailGrade);
			viewHolder.rlGrade = (RelativeLayout)convertView.findViewById(R.id.rlGrade);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		viewHolder.tvPeopleName.setText(peopleList[position]);

		final RelativeLayout rlGrade = viewHolder.rlGrade;
		final View finalConvertView = convertView;
		viewHolder.ivDown.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isDownList[position]) {
					isDownList[position] = false;
					rlGrade.setVisibility(View.INVISIBLE);
					((ImageView) v).setImageResource(R.drawable.ic_keyboard_arrow_left_black_48dp);
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
					finalConvertView.setLayoutParams(lp);
				} else {
					isDownList[position] = true;
					rlGrade.setVisibility(View.VISIBLE);
					((ImageView) v).setImageResource(R.drawable.ic_keyboard_arrow_down_black_48dp);
					AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 800);
					finalConvertView.setLayoutParams(lp);
				}
				finalConvertView.postInvalidate();
			}
		});

		if (isDownList[position]){
			rlGrade.setVisibility(View.VISIBLE);
			viewHolder.ivDown.setImageResource(R.drawable.ic_keyboard_arrow_down_black_48dp);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 800);
			finalConvertView.setLayoutParams(lp);
		} else {
			rlGrade.setVisibility(View.INVISIBLE);
			viewHolder.ivDown.setImageResource(R.drawable.ic_keyboard_arrow_left_black_48dp);
			AbsListView.LayoutParams lp = new AbsListView.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 150);
			finalConvertView.setLayoutParams(lp);
		}

		viewHolder.lvDetailGrade.setAdapter(new DetailGradeListViewAdapter(activity, groupId, gameName, peopleList[position]));
		final String selfName = (String)getItem(position);
		viewHolder.lvDetailGrade.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				new EditGradeDialogFragment().show(activity.getFragmentManager(), gameName + "###" + groupId + "###" + selfName + "###" + ((TextView)view.findViewById(R.id.tvOpponentName)).getText());

			}
		});
		return convertView;
	}

	class ViewHolder{
		TextView tvPeopleName;
		ImageView ivDown;
		ListView lvDetailGrade;
		RelativeLayout rlGrade;
	}
}
