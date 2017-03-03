package com.npaw.compressor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.npaw.utilities.args.Args;
import com.npaw.utilities.args.ArgsException;

public class CompressorApplication {


	public static void main(String[] args) {
		
		try{
			
			Args arg = new Args(args);
			String operation = arg.getOperation();
			String targetFile = arg.getTargetFile();
			String resultFile = arg.getResultFile();
			
			executeApplication(operation, targetFile, resultFile);
				
		}
		catch(ArgsException e){
			System.out.println("The parameters must be: -[c/d] file1 file2");
		} 	
		
	}
	
	private static void executeApplication(String operation, String targetFile, String resultFile){
		
			try {
				doCompression(targetFile, resultFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			List<Integer> list= buildList(targetFile);
//			List<String> compressedList = buildCompressedList(targetFile);
			
	}
	
	private static void doCompression(String targetFile, String resultFile) throws FileNotFoundException{
		
		String[] numberString;
		List<String> lines = getLines(targetFile);
				
		Integer number;
		String hex;
		for (String line : lines) {
			
			numberString = line.split(",");
			if (numberString.length > 0){
				
				for (int n=0; n< numberString.length; n++){
				
					if (isStringInteger(numberString[n])){
						number = Integer.valueOf(numberString[n]);
						hex = Integer.toHexString(number);
						appendToFile(hex+",", resultFile);
					} 					
				}
			}
		}		
			
	}
	
	private static String compressAString(String data){
		
		return data;
	}
	
	private static void appendToFile(String data, String fileName){

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {

			bw.write(data);

		} catch (IOException e) {

			e.printStackTrace();

		}

	}
	
	private static List<String> buildCompressedList(String targetFile){
		List<String> lines = getLines(targetFile);
		List<String> compressedNumbers = buildListCompressedNumbers(lines);
		return compressedNumbers;
	}
	
	private static List<Integer> buildList(String targetFile){
		List<String> lines = getLines(targetFile);
		List<Integer> numbers = buildListNumbers(lines);
		return numbers;
	}
	

	private static List<String> getLines(String filename) {

		Scanner fileScanner;
		List<String> lines = new ArrayList<String>();

		try {
				fileScanner = new Scanner(new File(filename));
				while (fileScanner.hasNextLine()) {
				lines.add(fileScanner.nextLine());
			}
			
			fileScanner.close();
		} catch (FileNotFoundException e) {}

		return lines;
	}

	private static List<String> buildListCompressedNumbers(List<String> lines){
		
		String[] numberString;
		List<String> compressedNumbers = new ArrayList<String>();
		Integer number;
		String hex;
		for (String line : lines) {
			
			numberString = line.split(",");
			if (numberString.length > 0){
				
				for (int n=0; n< numberString.length; n++){
				
					if (isStringInteger(numberString[n])){
						number = Integer.valueOf(numberString[n]);
						hex = Integer.toHexString(number);
						compressedNumbers.add(hex);
					} 					
				}
			}
		}
		return compressedNumbers;
	}
	
	private static List<Integer> buildListNumbers(List<String> lines){
	
		String[] numberString;
		List<Integer> numbers = new ArrayList<Integer>();
		Integer number;
		
		for (String line : lines) {
			
			numberString = line.split(",");
			if (numberString.length > 0){
				
				for (int n=0; n< numberString.length; n++){
				
					if (isStringInteger(numberString[n])){
						number = Integer.valueOf(numberString[n]);
						numbers.add(number);
					} 					
				}
			}
		}
		return numbers;
	}
	
	private static boolean isStringInteger(String stringToCheck){
		
		try{
			
			Integer number = Integer.valueOf(stringToCheck);
			//String s = Character.toString((char)c);
			
			System.out.print(" "+ number);
			System.out.println();
			String hex = Integer.toHexString(number);
			System.out.print(" "+ hex);
		}
		catch(Exception ex){
			return false;
		}
		return true;
	}
	
	
}
