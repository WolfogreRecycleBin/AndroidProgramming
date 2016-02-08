package wolfogre.kh6_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

	File path;
	File[] files;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				//TODO
			}
		});

		update(Environment.getExternalStorageDirectory());

		ListView listView=(ListView)this.findViewById(R.id.list_view);
		final Context context = this;

		// 注意此处有坑，如果元素中含有有点击事件的控件，如按钮，元素的点击将被屏蔽，详见
		// http://www.apkbus.com/android-121982-1-1.html
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (files[position].isDirectory()) {
					update(files[position]);
				} else {
					Intent intent = new Intent(MainActivity.this,ReadFileActivity.class);
					intent.putExtra("file_path", files[position].getAbsolutePath());
					startActivity(intent);
				}
			}
		});

		listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(context, "长长长长按了" + position, Toast.LENGTH_LONG).show();
				return true;
			}
		});
		new DescriptionDialogFragment().show(getFragmentManager(), "");
	}

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_BACK && !path.equals(Environment.getExternalStorageDirectory())){
			update(path.getParentFile());
			return true;
		}
		return super.onKeyUp(keyCode, event);
	}

	private void update(File newPath){
		path = newPath;
		files = new File(path.getPath()).listFiles();
		ArrayList<File> list =new ArrayList<>();
		Collections.addAll(list, files);
		ListView listView=(ListView)this.findViewById(R.id.list_view);
		listView.setAdapter(new ListAdapter(list, this));
		setTitle(path.getPath());
	}
}
