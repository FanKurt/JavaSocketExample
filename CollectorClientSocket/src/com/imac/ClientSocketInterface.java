package com.imac;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientSocketInterface extends Thread {
	private String IP;
	private int port;
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream output;

	public ClientSocketInterface(String ip, int port) {
		this.IP = ip;
		this.port = port;
		init();
	}

	private void init() {
		try {
			socket = new Socket(IP, port);
			input = new DataInputStream(socket.getInputStream());
			output = new DataOutputStream(socket.getOutputStream());
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			while(true){
				String data = input.readUTF();
				System.out.println(data);
			}
		} catch (Exception e) {
			System.out.println("Error ClientSocketInterface run() :"+ e.toString());
		}
	}

	public void Write(String data) {
		try {
			output.writeUTF(data);
			output.flush();
		} catch (Exception e) {
			System.out.println("Error ClientSocketInterface Write() :"+ e.toString());
		}
	}
}
