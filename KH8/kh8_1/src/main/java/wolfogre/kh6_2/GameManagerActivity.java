package wolfogre.kh6_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import wolfogre.kh8_1.*;

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
				GameInfoByJson.addGameName(etGameName.getText().toString());
				etGameName.setText("");
				etGameTime.setText("");
				updateGameList();
			}
		});

		((ListView)findViewById(R.id.lvGames)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(GameManagerActivity.this, GameSettingActivity.class);
				intent.putExtra(getString(R.string.intent_key_game_name), (String) ((ListView) parent).getAdapter().getItem(position));
				startActivity(intent);
			}
		});

		((ListView)findViewById(R.id.lvGames)).setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(GameManagerActivity.this, GroupSettingActivity.class);
				intent.putExtra(getString(R.string.intent_key_game_name), (String) ((ListView) parent).getAdapter().getItem(position));
				startActivity(intent);
				return true;
			}
		});
		updateGameList();
	}

	private void updateGameList(){
		((ListView)findViewById(R.id.lvGames)).setAdapter(new GameListViewAdapter(this));
	}
}
