package wolfogre.kh6_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Set;

public class GameSettingActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_setting);
		setTitle("比赛设置");

		final String gameName = getIntent().getStringExtra(getString(R.string.key_game_name));

		((TextView)(findViewById(R.id.tvGameName))).setText(gameName);

		SharedPreferences spGroupNumber = getSharedPreferences(getString(R.string.name_group_number), MODE_PRIVATE);
		Integer groupNumber = spGroupNumber.getInt(gameName, 0);

		SharedPreferences spGroupPeopleNumber = getSharedPreferences(getString(R.string.name_group_people_number), MODE_PRIVATE);
		Integer groupPeopleNumber = spGroupPeopleNumber.getInt(gameName, 0);

		final SharedPreferences.Editor speGroupNumber = getSharedPreferences(getString(R.string.name_group_number), MODE_PRIVATE).edit();
		final SharedPreferences.Editor speGroupPeopleNumber = getSharedPreferences(getString(R.string.name_group_people_number), MODE_PRIVATE).edit();

		NumberPicker npGroupNumber = (NumberPicker)findViewById(R.id.npGroupNumber);
		npGroupNumber.setMinValue(0);
		npGroupNumber.setMaxValue(5);
		npGroupNumber.setValue(groupNumber);
		npGroupNumber.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				speGroupNumber.putInt(gameName, newVal);
			}
		});

		NumberPicker npGroupPeopleNumber = (NumberPicker)findViewById(R.id.npGroupPeopleNumber);
		npGroupPeopleNumber.setMinValue(0);
		npGroupPeopleNumber.setMaxValue(10);
		npGroupPeopleNumber.setValue(groupPeopleNumber);
		npGroupPeopleNumber.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
				speGroupPeopleNumber.putInt(gameName, newVal);
			}
		});

		final GameSettingActivity gameSettingActivity = this;
		findViewById(R.id.btnSetGame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				speGroupNumber.apply();
				speGroupPeopleNumber.apply();
				gameSettingActivity.finish();
			}
		});
	}
}
