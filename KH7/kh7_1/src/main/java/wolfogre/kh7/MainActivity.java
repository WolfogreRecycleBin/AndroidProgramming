package wolfogre.kh7;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

	MyReceiverOfActivity myReceiverOfActivity;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		myReceiverOfActivity = new MyReceiverOfActivity(this);

		findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it=new Intent("wolfogre.kh7.MYSERVICE_ACTION");
				it.setPackage("wolfogre.kh7");
				startService(it);
			}
		});

		findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it=new Intent("wolfogre.kh7.MYSERVICE_ACTION");
				it.setPackage("wolfogre.kh7");
				stopService(it);
			}
		});

		findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent("wolfogre.kh7.MyReceiverOfService_ACTION");
				intent.setPackage("wolfogre.kh7");
				intent.putExtra("MESSAGE", "REQUEST_RANDOM_INTEGER");
				sendBroadcast(intent);
			}
		});

		myReceiverOfActivity = new MyReceiverOfActivity(this);
		IntentFilter intentFilter = new IntentFilter("wolfogre.kh7.MyReceiverOfActivity_ACTION");
		registerReceiver(myReceiverOfActivity, intentFilter);
	}

	@Override
	protected void onDestroy() {
		unregisterReceiver(myReceiverOfActivity);
		super.onDestroy();
	}

	public void onReceiveRandomInteger(int i){
		((TextView)(findViewById(R.id.textView3))).setText(String.format("%d", i));
	}
}
