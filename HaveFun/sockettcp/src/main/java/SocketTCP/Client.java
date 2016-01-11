package SocketTCP;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by Jason Song(wolfogre@outlook.com) on 01/09/2016.
 */
public abstract class Client {
	InetAddress inetAddress;
	int port;
	Socket socket;
	public Client(InetAddress inetAddress, int port){
		this.inetAddress =inetAddress;
		this.port =port;
	}

	public void connect() throws IOException {

		socket = new Socket(inetAddress , port);

		new Thread(new Runnable() {
			@Override
			public void run() {
				while(socket != null && socket.isConnected()){
					try {
						Scanner scanner = new Scanner(socket.getInputStream());
						if(scanner.hasNextLine())
							onConnected(scanner.nextLine());
					} catch (IOException e) {
						onConnected(e.getMessage());
						break;
					}
				}
			}
		}).start();
	}

	abstract void onConnected(String message);

	public void disconnect() {
		try{
			System.out.println("Disconnect::"+socket.getInetAddress()+":"+socket.getPort());
			if(socket != null && !socket.isConnected())
				socket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void send(String string) {
		try{
			PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
			printWriter.println(string);
			printWriter.flush();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
