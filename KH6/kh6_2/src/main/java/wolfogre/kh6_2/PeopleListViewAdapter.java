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
public class PeopleListViewAdapter extends BaseAdapter {

	String[] peopleNames;
	LayoutInflater layoutInflater;

	public PeopleListViewAdapter(Context context, int groupId, String gameName){
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
		Set<String> setString = spPeople.getStringSet(gameName, new HashSet<String>());
		peopleNames = new String[setString.size()];
		int index = 0;
		for(Object obj : setString.toArray()){
			peopleNames[index++] = (String)obj;
		}
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return peopleNames.length;
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
		TextView textView;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.item_people_list_view, null);
			textView = (TextView) convertView.findViewById(R.id.tvPeopleName);
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}
		textView.setText(peopleNames[position]);
		return convertView;
	}
}
