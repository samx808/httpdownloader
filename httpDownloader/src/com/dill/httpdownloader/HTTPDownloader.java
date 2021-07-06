package com.dill.httpdownloader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

public class HTTPDownloader {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
		
		downloader();
		
		Scanner repeatInput = new Scanner(System.in);
		System.out.print("Would you like to download more files? (Type 'yes' or 'no'): ");
		String repeat = repeatInput.nextLine();
//		System.out.println(repeat);
//		repeatInput.close();
		
		if (repeat.equals("no")) {
			System.out.println("Goodbye");
			System.exit(0);
		} else {
			main(args);
		}

	}
	
	@SuppressWarnings("resource")
	public static void downloader() throws IOException {
		
		try {	
			Scanner fileURLInput = new Scanner(System.in);
			System.out.print("URL of file to download: ");
			String fileURL = fileURLInput.nextLine();
//			System.out.println(fileURL);
//			fileURLInput.close();
			
			Scanner fileOutputUsrInput = new Scanner(System.in);
			System.out.print("Name of downloaded file (include extension): ");
			String filenameOutput = fileOutputUsrInput.nextLine();
//			fileOutputUsrInput.close();
			
			URL website = new URL(fileURL);
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			FileOutputStream fos = new FileOutputStream(filenameOutput);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			
//			fileURLInput.close();
//			fileOutputUsrInput.close();
			
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
			downloader();
		}
	}

}
