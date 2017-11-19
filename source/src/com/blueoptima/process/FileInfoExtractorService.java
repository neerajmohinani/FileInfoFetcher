package com.blueoptima.process;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.blueoptima.core.FileInfo;

/**
 *  A service which takes list of filenames as input and create multiple threads to extract the information of that file.
 *  
 *  @author Neeraj
 */
public class FileInfoExtractorService {
	
	private final int noOfThreads;
	private final List<String> fileNames;
	
	/**
	 * Constructor to create object of this service based on the list provided and no of threads.
	 * @param noOfThreads - no. of threads to be created for extracting the information
	 * @param fileNames
	 */
	public FileInfoExtractorService(int noOfThreads, List<String> fileNames) {
		super();
		this.noOfThreads = noOfThreads;
		this.fileNames = fileNames;
	}
	/**
	 * starts the threads to extract the information ans returns a list of FileInfo objects.
	 * @return
	 */
	public List<FileInfo> extract(){
		ExecutorService executor = Executors.newFixedThreadPool(noOfThreads);
		
		List<Future<FileInfo>> fileInfofutures = new ArrayList<>();
		List<FileInfo> fileInfoList = new ArrayList<>();
		
		//get name from FileInfo.com
		for(int index = 0; index < fileNames.size(); index++){
			fileInfofutures.add(executor.submit(new FileInfoExtractor(fileNames.get(index))));
		}
		
		
		for(int index = 0; index < fileInfofutures.size(); index++){
			try{
				FileInfo fileInfo= fileInfofutures.get(index).get();
				fileInfoList.add(fileInfo);
			}
			catch(Exception e){
				System.out.println("Problem in getting info from Filenfo.com for :"+ fileNames.get(index));
				e.printStackTrace();
				}
		}
		return fileInfoList;
	}
	
}
