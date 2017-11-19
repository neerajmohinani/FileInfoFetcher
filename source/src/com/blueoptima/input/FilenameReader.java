package com.blueoptima.input;

import java.util.List;
/**
 * 
 * Interface to be implemented by reader classes that read file names as inputs. 
 * @author Neeraj
 *
 */
public interface FilenameReader{
	public abstract List<String> readInputFileNames();
}
