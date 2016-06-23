package com.imac;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketInterface extends Thread {
	private int port;
	private ServerSocket server;
	private SparkSocketInterface SparkSocket;

	public ServerSocketInterface(int port) {
		this.port = port;
		init();
	}

	public void init() {
		try {
			server = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("ServerSocketInterface Error " + e);
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		if (server == null)
			System.out.println("Server Accept Error");
		try {
			while (true) {
				Socket client = server.accept();
				if (!IPConfig.SPARK_IP.equals(client.getInetAddress().getHostAddress())) {
					ClientSocketInterface csi = new ClientSocketInterface(ServerSocketInterface.this, client);
					csi.start();
				} else {
					SparkSocket = new SparkSocketInterface(client);
				}
			}

		} catch (IOException e) {
			System.out.println(e.toString());
			e.printStackTrace();
		}
	}

	public void WriteSpark(String data) {
		if (SparkSocket == null) {
			System.out.println("Spark Socket Error");
			return;
		}
		SparkSocket.Write(data);
	}

}
