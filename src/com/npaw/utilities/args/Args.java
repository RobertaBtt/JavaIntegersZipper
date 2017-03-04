package com.npaw.utilities.args;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class Args {

	String operation;
	String targetFile;
	String resultFile;
	
	
	public Args(String[] args) throws ArgsException {

		parseParameters(Arrays.asList(args));
	}

	private void parseParameters(List<String> argsList) throws ArgsException{
		ListIterator<String> currentArgument;
		
		try{
			operation = argsList.get(0).substring(1);
			targetFile = argsList.get(1);
			resultFile = argsList.get(2);
			
			
							
		}
		catch (Exception e){
			throw new ArgsException(e.getMessage());
		}
		
		
	}
	
	public String getOperation(){
		return operation;
	}
	
	public String getTargetFile(){
		return targetFile;
	}
	
	public String getResultFile(){
		return resultFile;
	}
	
	
}
