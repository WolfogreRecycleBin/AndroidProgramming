package wolfogre.kh6_2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.HashSet;
import java.util.Set;

public class GameManagerActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_manager);
		setTitle("全部比赛");
		final EditText etGameName = (EditText)findViewById(R.id.etGameName);
		final EditText etGameTime = (EditText)findViewById(R.id.etGameTime);
		findViewById(R.id.btnNewGame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				SharedPreferences spGame = getSharedPreferences(getString(R.string.name_games), MODE_PRIVATE);
				Set<String> stringSet = spGame.getStringSet(getString(R.string.key_game_name), new HashSet<String>());
				stringSet.add(etGameName.getText().toString());
				SharedPreferences.Editor speGame = getSharedPreferences(getString(R.string.name_games), MODE_PRIVATE).edit();
				speGame.putStringSet(getString(R.string.key_game_name),stringSet);
				speGame.apply();
				etGameName.setText("");
				etGameTime.setText("");
				updateGameList();
			}
		});
		((ListView)findViewById(R.id.lvGames)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(GameManagerActivity.this, GameSettingActivity.class);
				startActivity(intent);
			}
		});
		updateGameList();
	}

	private void updateGameList(){
		((ListView)findViewById(R.id.lvGames)).setAdapter(new GameListViewAdapter(this));
	}
}
