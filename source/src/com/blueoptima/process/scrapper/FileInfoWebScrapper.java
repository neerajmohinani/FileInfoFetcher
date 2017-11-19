package com.blueoptima.process.scrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.blueoptima.core.FileInfo;

/**
 * 
 * Scraps FileInfo.com and extracts information based on extension.
 * @author Neeraj
 *
 */
public class FileInfoWebScrapper extends WebScrapper<FileInfo> {

	static Map<String, FileInfo> cache = Collections.synchronizedMap(new HashMap<String, FileInfo>(10));
	static HashMap<String, Object> lockMap = new HashMap<>();
	private static final String URL = "https://fileinfo.com/extension/";
			
	public FileInfoWebScrapper(String extension) {
		super(URL, extension);
	}

	/**
	 * gets data from FileInfo.com and returns FileInfo object.
	 * @return FileInfo 
	 */
	@Override
	public FileInfo getData(){
		FileInfo infoFetched = null;
		if(cache.get(input) == null){
			if(lockMap.get(input) == null){
				synchronized(cache){
					if(lockMap.get(input) == null){
						lockMap.put(input, new FileInfo());
					}
				}
			}
			Object dummyObject = lockMap.get(input);
			
			synchronized(dummyObject){	
				if(cache.get(input) == null){
					infoFetched = scrap(input);
					infoFetched.setExtension(input);
					cache.put(input, infoFetched);				
				}
			}
		}
		
		infoFetched = cache.get(input);
		return infoFetched.clone();
	}
	
	private FileInfo scrap(String extension){
		FileInfo fileInfo = null;
		try {
			fileInfo = new FileInfo();
			fileInfo.setExtension(extension);
			Document doc = Jsoup.connect(URL+extension).userAgent("Mozilla").get(); 
			//get file type
			String filetype = doc.getElementsByClass("fileType").get(0).siblingElements().get(0).html();
			fileInfo.setFiletype(filetype);
			
			//get developer
			String developer = doc.getElementsByClass("headerInfo").get(0).child(0).child(0).child(1).html();
			fileInfo.setDeveloper(developer);
			
			//get popularity
			String popularity = doc.getElementsByClass("avgVote").get(0).html();
			fileInfo.setPopularity(Float.parseFloat(popularity));
			
			//get category
			String category = doc.getElementsByClass("headerInfo").get(0).child(0).child(2).child(1).child(0).html();
			fileInfo.setCategory(category);
			
			//get format
			String format = doc.getElementsByClass("headerInfo").get(0).child(0).child(3).child(1).child(0).html();
			fileInfo.setFormat(format);
			
			//get Information
			String information = doc.getElementsByClass("infoBox").get(0).child(0).child(0).html();
			information = replaceLinksWithInfo(information);
			fileInfo.setInformation(information);
			
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return fileInfo;
	}

	private String replaceLinksWithInfo(String information) {
		int startindex = information.indexOf("<a");
		while(startindex != -1){
			int endindex = information.indexOf("a>");
			String anchor = information.substring(startindex, endindex+2);
			String text = anchor.substring(anchor.indexOf(">")+1, anchor.lastIndexOf("<"));
			information = information.replace(anchor, text);
			startindex = information.indexOf("<a");
		}
		return information;
	}
}
