package wolfogre.kh6_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/17/2016.
 */
public class GridViewAdapter extends BaseAdapter {

	static Integer[] images= {
			R.mipmap.ic_add_to_photos_black_48dp,
			R.mipmap.ic_search_black_48dp,
			R.mipmap.ic_new_releases_black_48dp,
			R.mipmap.ic_announcement_black_48dp
	};
	static String[] texts = {
			"新比赛",
			"积分查询",
			"赛场新闻",
			"论坛"
	};
	LayoutInflater inflater;

	public GridViewAdapter(Context context)
	{
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return images.length;
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
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.item_grid_view, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			holder.textView = (TextView) convertView.findViewById(R.id.textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.imageView.setImageResource(images[position]);
		holder.textView.setText(texts[position]);

		return convertView;
	}
	private static class ViewHolder {
		ImageView imageView ;
		TextView textView;
	}
}
