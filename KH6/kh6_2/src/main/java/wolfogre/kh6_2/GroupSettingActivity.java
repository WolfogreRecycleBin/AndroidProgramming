package wolfogre.kh6_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class GroupSettingActivity extends AppCompatActivity {

	String gameName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_setting);

		setTitle("分组人员管理");

		gameName = getIntent().getStringExtra(getString(R.string.intent_key_game_name));

		((TextView)(findViewById(R.id.tvGameName))).setText(gameName);

		ListView lvGroups = (ListView) findViewById(R.id.lvGroups);

		final Context context = this;

		lvGroups.setAdapter(new GroupListViewAdapter(this, gameName));
		lvGroups.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(GroupSettingActivity.this, GradeSettingActivity.class);
				intent.putExtra(getString(R.string.intent_key_game_name), gameName);
				intent.putExtra(getString(R.string.intent_key_group_id), position);
				startActivity(intent);
				return true;
			}
		});
	}
}
