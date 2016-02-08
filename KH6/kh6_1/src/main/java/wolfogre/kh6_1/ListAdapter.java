package wolfogre.kh6_1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 02/08/2016.
 */
public class ListAdapter extends BaseAdapter {
	Context context;
	ArrayList<File> list;
	LayoutInflater inflater;

	public ListAdapter(ArrayList<File> list, Context context) {
		super();
		this.context = context;
		this.list = list;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.list_item, null);
			holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
			holder.textView = (TextView) convertView.findViewById(R.id.textView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(list.get(position).isFile()){
			holder.imageView.setImageResource(R.mipmap.ic_insert_drive_file_black_48dp);
		}else{
			holder.imageView.setImageResource(R.mipmap.ic_folder_open_black_48dp);
		}
		holder.textView.setText(list.get(position).getName());
		//holder.button = (Button) convertView.findViewById(R.id.button);

		return convertView;
	}

	private static class ViewHolder {
		ImageView imageView ;
		TextView textView;
		Button button;
	}
}
