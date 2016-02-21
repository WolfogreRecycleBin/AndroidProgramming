package wolfogre.kh6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/19/2016.
 */
public class DetailGradeListViewAdapter extends BaseAdapter {

	String[] opponentNames;
	String selfName;
	LayoutInflater layoutInflater;

	public DetailGradeListViewAdapter(Context context, int groupId, String gameName, String selfName){
		this.selfName = selfName;
		String groupName = "";
		switch (groupId){
			case 0:
				groupName = context.getString(R.string.name_group_0_people);
				break;
			case 1:
				groupName = context.getString(R.string.name_group_1_people);
				break;
			case 2:
				groupName = context.getString(R.string.name_group_2_people);
				break;
			case 3:
				groupName = context.getString(R.string.name_group_3_people);
				break;
			case 4:
				groupName = context.getString(R.string.name_group_4_people);
				break;
			default:
				Toast.makeText(context, "错误!", Toast.LENGTH_LONG).show();
		}
		SharedPreferences spPeople = context.getSharedPreferences(groupName, Context.MODE_PRIVATE);
		Set<String> setString = (spPeople.getStringSet(gameName, new HashSet<String>()));
		opponentNames = new String[setString.size() - 1];
		int index = 0;
		for(Object obj : setString.toArray()){
			if(!obj.equals(selfName))
				opponentNames[index++] = (String)obj;
		}
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return opponentNames.length;
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
			convertView = layoutInflater.inflate(R.layout.item_detail_grade_list_view, null);
			viewHolder.tvDetailGrade = (TextView) convertView.findViewById(R.id.tvDetailGrade);
			viewHolder.tvOpponentName = (TextView) convertView.findViewById(R.id.tvOpponentName);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvOpponentName.setText(opponentNames[position]);
		return convertView;
	}

	class ViewHolder{
		TextView tvDetailGrade;
		TextView tvOpponentName;
	}
}
