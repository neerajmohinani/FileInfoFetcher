package com.blueoptima;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import com.blueoptima.core.FileInfo;
import com.blueoptima.input.DirectoryFilenameReader;
import com.blueoptima.input.FilenameReader;
import com.blueoptima.input.XLSFilenameReader;
import com.blueoptima.output.FileInfoWriter;
import com.blueoptima.output.XLSFileInfoWriter;
import com.blueoptima.process.FileInfoExtractorService;

/**
 * 
 * The main class. Reads the the input, pass the data to process and writes the
 * output using FileInfoWriter
 * @author Neeraj
 *
 */
public class FileInfoFetcher {

	public static void main(String[] args) {
		try {
		    FilenameReader filenameReader = getInputFileReader();
		    
			List<String> filenames = filenameReader.readInputFileNames();
			System.out.println(filenames);
			FileInfoExtractorService extractorService = new FileInfoExtractorService(10, filenames);
			List<FileInfo> fileinfos = extractorService.extract();
			FileInfoWriter fileinfoWriter = new XLSFileInfoWriter(fileinfos);
			fileinfoWriter.write();
		
		} catch (Exception e) {
			System.out.println("Some Exception occurred. Program Exiting. Please try again");
			e.printStackTrace();
		}
		System.exit(0);
	}

	/**
	 * gets filename reader based on user's input
	 * @return FilenameReader object
	 * @throws IOException
	 */
	private static FilenameReader getInputFileReader() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
		FilenameReader reader = null;
		while(reader == null){
			System.out.println("Please enter the type of Input");
			System.out.println("Press 1 to get input from Excel sheet");
			System.out.println("Press 2 to get input by reading a directory");		
			String inputformat = br.readLine().trim();
			switch(inputformat){
			
			case "1":
				System.out.println("Please enter the complete path of Excel file");
				String excelFilePath = br.readLine().trim();
				reader = new XLSFilenameReader(excelFilePath);
				break;
			case "2":
				System.out.println("Please enter the complete path of the directory to scan for files");
				String directoryPath = br.readLine().trim();
				reader = new DirectoryFilenameReader(directoryPath);
				break;
			}
		}
		return reader;
	}
}
