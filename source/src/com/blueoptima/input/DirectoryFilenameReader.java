package com.blueoptima.input;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.FileUtils;
/**
 * A file name reader that scans all the directories and sub directories and provides a list of filenames. 
 * 
 * @author Neeraj
 * 
 *
 */
public class DirectoryFilenameReader implements FilenameReader{

	private final String DIR_TO_SCAN;
	
	public DirectoryFilenameReader(String DIR_TO_SCAN){
		this.DIR_TO_SCAN = DIR_TO_SCAN;
	}
	@Override
	public List<String> readInputFileNames() {
		File dir = new File(DIR_TO_SCAN);
		List<String> filenames = new ArrayList<>();
		Collection<File> files = FileUtils.listFiles(dir, null, true);
		for(File file : files){
			if(file.isFile()){
				filenames.add(file.getName());
			}
		}
		return filenames;
	}

}
