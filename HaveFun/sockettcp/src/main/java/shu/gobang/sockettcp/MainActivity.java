package shu.gobang.sockettcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import SocketTcpClient.AndroidClient;

public class MainActivity extends AppCompatActivity {

	AndroidClient androidClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			androidClient = new AndroidClient( InetAddress.getByName("115.28.191.67"), 6789, this);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		final Button btnConnect = (Button)findViewById(R.id.button);
		btnConnect.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							androidClient.connect();
						} catch (final IOException e) {
							btnConnect.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(getApplicationContext(), "连接失败:" + e.getMessage(), Toast.LENGTH_LONG).show();
								}
							});
							return;
						}
						btnConnect.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(getApplicationContext(), "连接成功", Toast.LENGTH_LONG).show();
							}
						});
					}
				}).start();
			}
		});
		final Button btnSend = (Button)findViewById(R.id.button2);
		final EditText etSend = (EditText)findViewById(R.id.editText);
		btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							androidClient.send(etSend.getText().toString());
						} catch (final IOException e) {
							btnConnect.post(new Runnable() {
								@Override
								public void run() {
									Toast.makeText(getApplicationContext(), "发送失败:" + e.getMessage(), Toast.LENGTH_LONG).show();
								}
							});
						}
					}
				}).start();
			}
		});
	}
}
