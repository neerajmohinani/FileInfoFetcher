package com.blueoptima.process.scrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.blueoptima.core.FileInfo;

/**
 * Scraps Fileext.com and extracts information based on extension.
 * @author Neeraj
 *
 */
public class FileExtScrapper extends WebScrapper<FileInfo> {
	
	static Map<String, FileInfo> cache = Collections.synchronizedMap(new HashMap<String, FileInfo>(10));
	static HashMap<String, Object> lockMap = new HashMap<>();
	private static final String URL = "http://filext.com/file-extension/";
			
	public FileExtScrapper(String extension) {
		super(URL, extension);
	}

	/**
	 * Scraps Fileext.com and returns the language or text with which this file is primary associated. 
	 * @return String
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
		if(infoFetched == null)
			System.out.println("returning null from FILEEXT for "+ input);
		return infoFetched;
	}

	private FileInfo scrap(String extension){
		FileInfo fileinfo = new FileInfo();
		String language = null;
		try {

			Document doc = Jsoup.connect(URL+extension).userAgent("Mozilla").get();

			//get file's primary association
			Elements languageElement = doc.select("#extended-info > div");
			if(languageElement != null){
				language = languageElement.get(0).html();
				if(language !=null){
					language = removeTags(language);
					language =  replaceLinksWithInfo(language);
				}
				fileinfo.setPrimaryAssociation(language);
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return fileinfo;
	}

	private String removeTags(String language) {
		int index = language.indexOf("</strong>");
		while(index != -1){
			String text = language.substring(index+9);
			language = text;
			index = language.indexOf("</strong>");
		}
		return language;
	}
	private String replaceLinksWithInfo(String data) {
		int startindex = data.indexOf("<a");
		while(startindex != -1){
			int endindex = data.indexOf("a>");
			String anchor = data.substring(startindex, endindex+2);
			String text = anchor.substring(anchor.indexOf(">")+1, anchor.lastIndexOf("<"));
			data = data.replace(anchor, text);
			startindex = data.indexOf("<a");
		}
		
		return data;
	}
}
