package SocketTcpClient;

import android.widget.Toast;

import java.net.InetAddress;

import shu.gobang.sockettcp.MainActivity;
import shu.gobang.sockettcp.R;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 01/11/2016.
 */
public class AndroidClient extends Client {

	MainActivity mainActivity;

	public AndroidClient(InetAddress inetAddress, int port, MainActivity mainActivity) {
		super(inetAddress, port);
		this.mainActivity = mainActivity;
	}

	@Override
	void onReceive(final String message) {
		mainActivity.findViewById(R.id.button).post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(mainActivity.getApplicationContext(), "收到:" + message, Toast.LENGTH_LONG).show();
			}
		});
	}
}
