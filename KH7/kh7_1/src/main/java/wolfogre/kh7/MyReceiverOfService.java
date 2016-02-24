package wolfogre.kh7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiverOfService extends BroadcastReceiver {

	MyService myService;

	public MyReceiverOfService(MyService myService) {
		this.myService = myService;
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String message = intent.getStringExtra("MESSAGE");
		if(message.equals("REQUEST_RANDOM_INTEGER")){
			myService.sendRandomInteger();
		}
	}
}
