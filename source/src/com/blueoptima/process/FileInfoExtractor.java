package com.blueoptima.process;

import java.util.concurrent.Callable;

import com.blueoptima.core.FileInfo;
import com.blueoptima.process.scrapper.FileExtScrapper;
import com.blueoptima.process.scrapper.FileInfoWebScrapper;
import com.blueoptima.process.scrapper.WebScrapper;
import com.blueoptima.util.FileInfoUtil;
/**
 * 
 * A simple Callable that calls different web scrapers and merges the information to be shown as output.
 * @author Neeraj
 *
 */
public class FileInfoExtractor implements Callable<FileInfo>{

	private final String fileName;
	public FileInfoExtractor(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public FileInfo call(){
		String ext = FileInfoUtil.getExtension(fileName);
		String name = FileInfoUtil.getName(fileName);
		FileInfo fileInfo;
		FileInfo fileInfo1 = null;
		FileInfo fileInfo2 = null;
		if(ext != null){
			System.out.println("reading information from fileinfo.com about file:" +fileName);
			WebScrapper<FileInfo> fileInfoScrapper = new FileInfoWebScrapper(ext);
			fileInfo1 = fileInfoScrapper.getData();
			System.out.println("reading information from filext.com for file:"+ fileName);
			WebScrapper<FileInfo> fileExtScrapper = new FileExtScrapper(ext);
			fileInfo2 = fileExtScrapper.getData();
		}
		if(fileInfo1 == null && fileInfo2 == null){
			fileInfo = new FileInfo();
		}
		else if(fileInfo1 == null){
			fileInfo = fileInfo2;
		}
		else {
			fileInfo = fileInfo1;
			if(fileInfo2 != null)
				fileInfo.setPrimaryAssociation(fileInfo2.getPrimaryAssociation());
		}
		fileInfo.setName(name);
		
		return fileInfo;
	}

}
