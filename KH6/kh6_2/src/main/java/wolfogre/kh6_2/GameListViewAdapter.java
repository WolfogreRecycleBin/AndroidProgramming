package wolfogre.kh6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/17/2016.
 */
public class GameListViewAdapter extends BaseAdapter {
	String[] gameNames;
	LayoutInflater layoutInflater;

	public GameListViewAdapter(Context context){
		SharedPreferences spGame = context.getSharedPreferences(context.getString(R.string.name_games), Context.MODE_PRIVATE);
		Set<String> setString = spGame.getStringSet(context.getString(R.string.key_game_name), new HashSet<String>());
		gameNames = new String[setString.size()];
		int index = 0;
		for(Object obj : setString.toArray()){
			gameNames[index++] = (String)obj;
		}
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return gameNames.length;
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
			convertView = layoutInflater.inflate(R.layout.item_game_list_view, null);
			textView = (TextView) convertView.findViewById(R.id.tvGameName);
			convertView.setTag(textView);
		} else {
			textView = (TextView) convertView.getTag();
		}
		textView.setText(gameNames[position]);
		return convertView;
	}
}
