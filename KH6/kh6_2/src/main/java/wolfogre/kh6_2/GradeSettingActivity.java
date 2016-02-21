package wolfogre.kh6_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class GradeSettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grade_setting);
		setTitle("比分记录");

		String gameName = getIntent().getStringExtra(getString(R.string.key_game_name));
		int groupId = getIntent().getIntExtra(getString(R.string.key_group_id), 0);

		((TextView)findViewById(R.id.tvGroupName)).setText("第" + (groupId + 1) + "组");

		((ListView)findViewById(R.id.lvGrade)).setAdapter(new GradeListViewAdapter(this, gameName, groupId));
	}
}
