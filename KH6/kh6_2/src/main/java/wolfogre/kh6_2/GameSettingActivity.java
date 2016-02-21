package wolfogre.kh6_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

public class GameSettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setting);
		setTitle("比赛设置");

		final String gameName = getIntent().getStringExtra(getString(R.string.intent_key_game_name));

		((TextView)(findViewById(R.id.tvGameName))).setText(gameName);

		NumberPicker npGroupNumber = (NumberPicker)findViewById(R.id.npGroupNumber);
		npGroupNumber.setMinValue(0);
		npGroupNumber.setMaxValue(5);
		npGroupNumber.setValue(GameInfoBySharedPreferences.getGroupNumber(gameName));
		npGroupNumber.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				GameInfoBySharedPreferences.setGroupNumber(gameName, newVal);
			}
		});

		NumberPicker npGroupPeopleNumber = (NumberPicker)findViewById(R.id.npGroupPeopleNumber);
		npGroupPeopleNumber.setMinValue(0);
		npGroupPeopleNumber.setMaxValue(10);
		npGroupPeopleNumber.setValue(GameInfoBySharedPreferences.getMaxPeopleNumber(gameName));
		npGroupPeopleNumber.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				GameInfoBySharedPreferences.setMaxPeopleNumber(gameName, newVal);
			}
		});

		final GameSettingActivity gameSettingActivity = this;
		findViewById(R.id.btnSetGame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				gameSettingActivity.finish();
			}
		});
	}
}
