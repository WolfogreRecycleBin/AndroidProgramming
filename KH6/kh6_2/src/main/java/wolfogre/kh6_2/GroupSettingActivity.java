package wolfogre.kh6_2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class GroupSettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_setting);

		setTitle("分组人员管理");

		String gameName = getIntent().getStringExtra(getString(R.string.key_game_name));

		((TextView)(findViewById(R.id.tvGameName))).setText(gameName);

		ListView lvGroups = (ListView) findViewById(R.id.lvGroups);

		final Context context = this;

		lvGroups.setAdapter(new GroupListViewAdapter(this, gameName));
		lvGroups.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(context, "长按了" + position, Toast.LENGTH_LONG).show();
				return true;
			}
		});
	}
}
