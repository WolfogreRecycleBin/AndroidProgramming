package wolfogre.kh8_1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import wolfogre.kh6_2.*;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("比赛管理主界面");

		GridView gridView = (GridView)findViewById(R.id.gridView);

		final GridViewAdapter gridViewAdapter = new GridViewAdapter(this);

		gridView.setAdapter(gridViewAdapter);

		final Context context = this;
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(context, "你点击了" + gridViewAdapter.getItem(position), Toast.LENGTH_LONG).show();
				if (position == 0) {
					Intent intent = new Intent(MainActivity.this, GameManagerActivity.class);
					startActivity(intent);
				}
			}
		});

		GameInfoBySharedPreferences.setContext(this);

		new TipsDialogFragment().show(getFragmentManager(), getString(R.string.app_warning));
		new TipsDialogFragment().show(getFragmentManager(), getString(R.string.app_description));

	}
}
