package com.blueoptima.process.scrapper;


public abstract class WebScrapper<T> {

	protected String URL;
	protected String input;
	public WebScrapper(String URL, String input){
		this.URL = URL;
		this.input = input;
	}	
	public abstract T getData();
}
