package com.imac;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientSocketInterface extends Thread {
	private Socket client;
	private ServerSocketInterface SSI;
	private String socketIP;
	private DataInputStream input;
	private DataOutputStream output;

	public ClientSocketInterface(ServerSocketInterface SSI, Socket client) {
		this.client = client;
		this.SSI = SSI;
		socketIP = client.getInetAddress().getHostAddress();
		System.out.println("Client IP:" + socketIP);
		init();
	}

	private void init() {
		try {
			input = new DataInputStream(client.getInputStream());
			output = new DataOutputStream(client.getOutputStream());
		} catch (IOException e) {
			System.out.print("IOException  : " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {

		try {
			while (true) {
				String data = input.readUTF();
				if (data == null)
					return;
				System.out.println("Client Socket IP : " + socketIP
						+ "  Data :" + data);
				 if (SSI != null)
					 SSI.WriteSpark(data);
			}
		} catch (IOException e) {
			System.out.println("IOException ClientSocketInterface run() : "+e);
			e.printStackTrace();
		}
	}

	public void Write(String data) {
		try {
			output.writeUTF(data);
			output.flush();
		} catch (Exception e) {
			System.out.println("Exception ClientSocketInterface Write() : "+e);
			e.printStackTrace();
		}
	}
}
