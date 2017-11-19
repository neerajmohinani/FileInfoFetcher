package com.blueoptima.process.scrapper;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class GoogleScrapper extends WebScrapper<String>{

	private static String URL ="https://www.google.com/search?q=what+is+";
	public GoogleScrapper(String input) {
		super(URL, input);
	}
	@Override
	public String getData() {
		String usage = null;
		try {
			Document doc = Jsoup.connect(URL+this.input).userAgent("Mozilla").get();
			Elements usageElement = doc.getElementsByClass("_Tgc");
			if(usageElement != null && usageElement.size() != 0){
				usage = usageElement.get(0).html();
				usage = removeBoldsTags(usage);
			}
		} catch (IOException e) {
			System.out.println("Exception occured while fetching data from google for"+ input);
			e.printStackTrace();
		}
		
		return usage;
	}
	
	private String removeBoldsTags(String information) {
		int startindex = information.indexOf("<b");
		System.out.println("replacing bold tag");
		while(startindex != -1){
			int endindex = information.indexOf("b>");
			String anchor = information.substring(startindex, endindex+2);
			String text = anchor.substring(anchor.indexOf(">")+1, anchor.lastIndexOf("<"));
			information = information.replace(anchor, text);
			startindex = information.indexOf("<b");
		}
		System.out.println("replaced bold tag, info:" + information);
		return information;
	}
	
}
