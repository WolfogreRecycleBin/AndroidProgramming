package wolfogre.kh6_2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setTitle("比赛管理主界面");

		GridView gridView = (GridView)findViewById(R.id.gridView);
		gridView.setAdapter(new GridViewAdapter(this));

		final Context context = this;
		gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(context,"你点击了" + GridViewAdapter.texts[position], Toast.LENGTH_LONG).show();
				if(position == 0){
					Intent intent = new Intent(MainActivity.this,GameManagerActivity.class);
					startActivity(intent);
				}
			}
		});
		new DescriptionDialogFragment().show(getFragmentManager(), "");
	}
}
