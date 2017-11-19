package com.blueoptima.output;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.blueoptima.core.FileInfo;
/**
 * A excel sheet writer which writes a list of file info objects to excel sheet. 
 * 
 * @author Neeraj
 *
 */
public class XLSFileInfoWriter extends FileInfoWriter{

	public XLSFileInfoWriter(List<FileInfo> fileInfoList) {
		super(fileInfoList);
	}

	@Override
	public void write() {
		FileOutputStream outputStream = null;
		String excelname = (new File(".").getAbsoluteFile()).getPath();
		excelname = excelname.substring(0,excelname.length()-2) + "\\Report";
		
		File f = new File(excelname+".xlsx");
		int i =1;
		while(f.exists() ) { 
		    f = new File(excelname+i+".xlsx");
		    i++;
		}
		
	        XSSFWorkbook workbook = new XSSFWorkbook();
	        XSSFSheet sheet = workbook.createSheet("Files Information");

	        int rowNum = 0;
	        System.out.println("Creating Report");
	        System.out.println("writing report to :"+f.getPath());
	        int colNum = 0;
	        Row row = sheet.createRow(rowNum++);
	        Cell cell = row.createCell(colNum++);
            cell.setCellValue("File Name");
            cell = row.createCell(colNum++);
            cell.setCellValue("Extension");
            cell = row.createCell(colNum++);
            cell.setCellValue("File Type");
            cell = row.createCell(colNum++);
            cell.setCellValue("Associated with"); 
            cell = row.createCell(colNum++);      
            cell.setCellValue("Category");
            cell = row.createCell(colNum++);
            cell.setCellValue("Developer");
            cell = row.createCell(colNum++);
            cell.setCellValue("Format");
            cell = row.createCell(colNum++);
            cell.setCellValue("Popularity");
            cell = row.createCell(colNum++);
            cell.setCellValue("Information"); 
            
	        for (FileInfo fileInfo : fileInfoList) {
	            row = sheet.createRow(rowNum++);
	            colNum = 0;
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getName());
	            cell = row.createCell(colNum++);
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getExtension());
	            cell.setCellValue(fileInfo.getFiletype());
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getPrimaryAssociation()); 
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getCategory());
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getDeveloper());
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getFormat());
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getPopularity());
	            cell = row.createCell(colNum++);
	            cell.setCellValue(fileInfo.getInformation());            
	                       
	        }
	       	

	        try {
	            outputStream = new FileOutputStream(f);
	            workbook.write(outputStream);
	            outputStream.close();
	            workbook.close();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	        System.out.println("Done");
	
	}
}
