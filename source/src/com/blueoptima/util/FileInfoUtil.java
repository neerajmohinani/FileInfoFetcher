package com.blueoptima.util;
/**
 * Utility class to used by the application
 * 
 * @author Neeraj
 *
 */
public class FileInfoUtil {

	public static String getExtension(String fileName){
		String extension = null;
		if(fileName.lastIndexOf(".") != -1)
		extension = fileName.substring(fileName.lastIndexOf(".")+1);
		return extension;
	}
	public static String getName(String fileName){
		String name = fileName;
		if(fileName.indexOf(".") != -1)
		name = fileName.substring(0,fileName.lastIndexOf("."));
		return name;
	}
}
