package com.imac;

import java.util.Scanner;

public class ServerMain {
	public static void main(String[] args) throws Exception {
		// args[0] : Spark IP
		// args[1] : Server Port
		if (args.length != 2) {
			System.out.println("Format Error : [Spark IP] [Spark Port]");
			return;
		}
		IPConfig.SPARK_IP = args[0];
		ServerSocketInterface SSI = new ServerSocketInterface(Integer.valueOf(args[1]));
		SSI.start();
	}

}
