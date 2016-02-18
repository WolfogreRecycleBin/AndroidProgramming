package wolfogre.kh6_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class GroupSettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group_setting);

		setTitle("分组人员管理");

		String gameName = getIntent().getStringExtra(getString(R.string.key_game_name));

		((TextView)(findViewById(R.id.tvGameName))).setText(gameName);

		((ListView)findViewById(R.id.lvGroups)).setAdapter(new GroupListViewAdapter(this, gameName));
	}
}
