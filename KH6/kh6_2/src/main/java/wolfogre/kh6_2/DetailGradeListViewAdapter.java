package wolfogre.kh6_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/19/2016.
 */
public class DetailGradeListViewAdapter extends BaseAdapter {

	String[] opponentNames;
	String[] grades;
	String selfName;
	LayoutInflater layoutInflater;

	public DetailGradeListViewAdapter(Context context, int groupId, String gameName, String selfName){
		this.selfName = selfName;
		opponentNames = GameInfoBySharedPreferences.getOpponentNames(gameName, groupId, selfName);

		grades = new String[opponentNames.length];
		for(int i = 0; i < grades.length; ++i){
			grades[i] = GameInfoBySharedPreferences.getGrade(gameName, groupId, selfName, opponentNames[i]);
		}

		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return opponentNames.length;
	}

	@Override
	public Object getItem(int position) {
		return opponentNames[position];
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
			convertView = layoutInflater.inflate(R.layout.item_detail_grade_list_view, null);
			viewHolder.tvDetailGrade = (TextView) convertView.findViewById(R.id.tvDetailGrade);
			viewHolder.tvOpponentName = (TextView) convertView.findViewById(R.id.tvOpponentName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvOpponentName.setText(opponentNames[position]);
		viewHolder.tvDetailGrade.setText(grades[position]);
		return convertView;
	}

	class ViewHolder{
		TextView tvDetailGrade;
		TextView tvOpponentName;
	}
}
