package com.blueoptima.core;

/**
 *  A simple class to store information about files.
 *  
 * @author Neeraj 
 *
 */
public class FileInfo implements Cloneable{

	private String name;
	private String extension;
	private String information;
	private String category;
	private String format;
	private String filetype;
	private String developer;
	private float popularity;
	private String primaryAssociation;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getExtension() {
		return extension;
	}
	public void setExtension(String extension) {
		this.extension = extension;
	}
	public String getInformation() {
		return information;
	}
	public void setInformation(String information) {
		this.information = information;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getFiletype() {
		return filetype;
	}
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	public String getDeveloper() {
		return developer;
	}
	public void setDeveloper(String developer) {
		this.developer = developer;
	}
	public float getPopularity() {
		return popularity;
	}
	public void setPopularity(float popularity) {
		this.popularity = popularity;
	}
	
	@Override
	public String toString() {
		return "FileInfo [name=" + name + ", extension=" + extension + "]";
	}
	
	
	public String getPrimaryAssociation() {
		return primaryAssociation;
	}
	public void setPrimaryAssociation(String primaryAssociation) {
		this.primaryAssociation = primaryAssociation;
	}
	@Override
	public FileInfo clone() 
	{
		FileInfo fileInfo = null;
		try {
			fileInfo = (FileInfo) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return fileInfo;
	}
	
}
