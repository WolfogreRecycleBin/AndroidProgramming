package wolfogre.kh6_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import wolfogre.kh8_1.*;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/17/2016.
 */
public class GameListViewAdapter extends BaseAdapter {
	String[] gameNames;
	LayoutInflater layoutInflater;

	public GameListViewAdapter(Context context){
		gameNames = GameInfoByJson.getGameNames();
		layoutInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return gameNames.length;
	}

	@Override
	public Object getItem(int position) {
		return gameNames[position];
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
