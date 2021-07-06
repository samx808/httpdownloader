package com.dill.httpdownloader;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;


public class HTTPDownloader {
	
	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		
		downloader();
		
		System.out.print("Would you like to download more files? (Type 'yes' or 'no'): ");
		String repeat = scanner.nextLine();
		
		if (repeat.equals("no")) {
			System.out.println("Goodbye");
			System.exit(0);
		} else {
			main(args);
		}

	}
	
	public static void downloader() throws IOException {
		
		try {	
			
			System.out.print("URL of file to download: ");
			String fileURL = scanner.nextLine();
			
			System.out.print("Name of downloaded file (include extension): ");
			String filenameOutput = scanner.nextLine();
			
			
				URL website = new URL(fileURL);
				ReadableByteChannel rbc = Channels.newChannel(website.openStream());
				FileOutputStream fos = new FileOutputStream(filenameOutput);
				fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
				fos.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("Invalid Filename");
			downloader();
		}
			
			
		catch (MalformedURLException e) {
			System.out.println("Invalid URL");
			downloader();
		}
	}

}