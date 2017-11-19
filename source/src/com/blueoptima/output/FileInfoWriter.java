package com.blueoptima.output;

import java.util.List;

import com.blueoptima.core.FileInfo;
/**
 * Interface to be implemented by wrtier classes that write fileinfo to be shown as output.
 * @author Neeraj
 *
 */
public abstract class FileInfoWriter {
	protected List<FileInfo> fileInfoList;
	
	FileInfoWriter(List<FileInfo> fileInfoList){
		this.fileInfoList = fileInfoList;
	}
	
	public abstract void write();
	
}
