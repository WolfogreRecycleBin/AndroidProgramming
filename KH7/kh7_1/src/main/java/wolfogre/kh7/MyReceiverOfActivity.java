package wolfogre.kh7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

public class MyReceiverOfActivity extends BroadcastReceiver {

	MainActivity mainActivity;

	public MyReceiverOfActivity(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = intent.getStringExtra("MESSAGE");
		if(message.equals("SEND_RANDOM_INTEGER")){
			mainActivity.onReceiveRandomInteger(intent.getIntExtra("RANDOM_INTEGER", 0));
		}
	}
}
