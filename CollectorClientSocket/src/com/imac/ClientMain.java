package com.imac;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class ClientMain {
	private static int lastestIndex=0;
	private static ClientSocketInterface CSI ;
	public static void main(String args[]) {
		
		// args[0] : File Path
		// args[1] : Server ip
		// args[2] : Server port
		
		if(args.length!=3){
			System.out.print("Format Error : [File Path] [Server IP] [Server Port]");
			return;
		}
		
		CSI = new ClientSocketInterface(args[1], Integer.parseInt( args[2]));
		TimerTask task = new FileWatcher(new File(args[0])) {
			protected void onChange(File file) {
				System.out.println("File " + file.getName() + " have change !");
				ReadFile(file.getAbsolutePath());
			}
		};

		Timer timer = new Timer();
		timer.schedule(task, new Date(), 1000);
	}
	
	private static void ReadFile(String path) {
		BufferedReader br = null;
		int curIndex=0;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(path));
			while ((sCurrentLine = br.readLine()) != null) {
				curIndex++;
				if(lastestIndex == 0 || curIndex > lastestIndex ){
					if(!sCurrentLine.trim().equals(""))
					CSI.Write(sCurrentLine);
				}
			}
			lastestIndex = curIndex;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
