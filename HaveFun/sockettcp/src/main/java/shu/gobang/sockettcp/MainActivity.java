package shu.gobang.sockettcp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import SocketTCP.Client;

public class MainActivity extends AppCompatActivity {

	Client client;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			//client = new Client( InetAddress.getByName("120.27.99.15"),6789);
			//TODO:dame it.
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
							client.connect();
						} catch (IOException e) {
							printOnView("连接失败");
							return;
						}
						printOnView("连接成功");
					}
				}).start();
			}
		});
		final Button btnSend = (Button)findViewById(R.id.button);
		final EditText etSend = (EditText)findViewById(R.id.editText);
		btnSend.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							client.send();
						} catch (IOException e) {
							printOnView("连接失败");
							return;
						}
						printOnView("连接成功");
					}
				}).start();
			}
		});
	}

	void printOnView(final String message){
		final TextView txt = (TextView)findViewById(R.id.textView2);
		txt.post(new Runnable() {
			@Override
			public void run() {
				txt.setText(message + "\n" + txt.getText());
			}
		});
	}
}
