package com.imac;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class SparkSocketInterface extends Thread {
	private Socket client;
	private PrintWriter output;

	public SparkSocketInterface(Socket client) {
		this.client = client;
		System.out.println("IP:" + client.getInetAddress().getHostAddress());
		init();
	}

	private void init() {
		try {
			output = new PrintWriter(client.getOutputStream());
		} catch (IOException e) {
			System.out.print("IOException  : " + e);
			e.printStackTrace();
		}
	}

	public void Write(String data) {
		try {
			System.out.println("Write to Spark Data : "+data);
			output.println(data);
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
