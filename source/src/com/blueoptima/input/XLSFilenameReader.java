package com.blueoptima.input;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *  An Excel sheet reader that reads all the filenames provided that sheet.
 *  Excel sheet should contain filenames as in  fisr column of different rows.
 * @author Neeraj
 *
 */
public class XLSFilenameReader implements FilenameReader{
	private final String FILE_NAME;
	public XLSFilenameReader(String FILE_NAME) {
		this.FILE_NAME = FILE_NAME;
	}

	@Override
	public List<String> readInputFileNames() {
		List<String> filenames = new ArrayList<>();
		try {
		FileInputStream inputStream = new FileInputStream(new File(FILE_NAME));
			 
		    Workbook workbook = new XSSFWorkbook(inputStream);
		    Sheet firstSheet = workbook.getSheetAt(0);
		    Iterator<Row> iterator = firstSheet.iterator();
		    
		    while (iterator.hasNext()) {
		     Row nextRow = iterator.next();  
		     Cell cell = nextRow.getCell(0);
		     
		     
		     if(cell.getStringCellValue() != null)
		    	 filenames.add( cell.getStringCellValue().trim());
		    }

			workbook.close();
			inputStream.close();
	    } catch (IOException e) {
	    	System.out.println("Exception occured while reading Excel File.");
			e.printStackTrace();
		}
	 
	    return filenames;
	}

}
