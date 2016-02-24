package wolfogre.kh7;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Random;

public class MyService extends Service {

	MyReceiverOfService myReceiverOfService;

	void sendRandomInteger(){
		Intent newIntent = new Intent("wolfogre.kh7.MyReceiverOfActivity_ACTION");
		newIntent.setPackage("wolfogre.kh7");
		newIntent.putExtra("MESSAGE","SEND_RANDOM_INTEGER");
		newIntent.putExtra("RANDOM_INTEGER",new Random().nextInt());
		getBaseContext().sendBroadcast(newIntent);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return  null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		myReceiverOfService = new MyReceiverOfService(this);
		IntentFilter intentFilter = new IntentFilter("wolfogre.kh7.MyReceiverOfService_ACTION");
		getBaseContext().registerReceiver(myReceiverOfService, intentFilter);
		Toast.makeText(getBaseContext(), "服务已启动!", Toast.LENGTH_LONG).show();
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		Toast.makeText(getBaseContext(), "服务已关闭!", Toast.LENGTH_LONG).show();
		getBaseContext().unregisterReceiver(myReceiverOfService);
		super.onDestroy();
	}
}
